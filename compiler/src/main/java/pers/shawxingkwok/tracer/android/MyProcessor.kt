package pers.shawxingkwok.tracer.android

import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import pers.shawxingkwok.ksputil.*
import pers.shawxingkwok.ktutil.updateIf
import pers.shawxingkwok.tracer.Tracer
import pers.shawxingkwok.tracer.shared.*

@Provide
internal object MyProcessor : KSProcessor {
    override fun process(round: Int): List<KSAnnotated> {
        if (round == 0) process()
        return emptyList()
    }
}

// add 'override val xx get() = requireActivity()/requireParentFragment()' in 'XxFragmentTracer'
private fun process(){
    // return if error just occurred in tracer
    if (!Tags.interfacesBuilt) return

    val (activityKSType, fragmentKSType) =
        arrayOf(
            "androidx.appcompat.app.AppCompatActivity",
            "androidx.fragment.app.Fragment",
        )
        .map { resolver.getClassDeclarationByName(it)!!.asStarProjectedType() }

    val wronglyAnnotatedFragmentKSClasses = mutableListOf<KSClassDeclaration>()

    resolver.getAnnotatedSymbols<Tracer.Nodes, KSClassDeclaration>()
        .filter { fragmentKSType.isAssignableFrom(it.asStarProjectedType()) }
        .mapNotNull { ksClass ->
            val context = ksClass.context!!
            val contextType = context.asStarProjectedType()
            when {
                activityKSType.isAssignableFrom(contextType) -> Triple(ksClass, context, "requireActivity()")
                fragmentKSType.isAssignableFrom(contextType) -> Triple(ksClass, context, "requireParentFragment()")
                else -> {
                    wronglyAnnotatedFragmentKSClasses += ksClass
                    null
                }
            }
        }
        .forEach { (ksClass, context, requirement) ->
            val declHeader = "    override val `__${getRootNodesPropName(context)}` get() = "

            val cast = buildString {
                append(context.noPackageName()!!)
                if (context.typeParameters.any()){
                    append("<")
                    append(context.typeParameters.joinToString(", "){ "*" })
                    append(">")
                }
            }

            val declBody = "`_${getRootNodesPropName(ksClass)}`.$requirement as $cast"
            val outerDeclBody = declBody.replaceFirst("`", "`_")

            val pathEnding = ksClass
                .packageName().replace(".", "/")
                .updateIf({ it.any() }){ it.plus("/") }
                .plus("${ksClass.noPackageName()}${Names.Tracer}s.kt")

            val correspondingFile = Environment.codeGenerator.generatedFile
                .first { it.path.endsWith(pathEnding) }

            val lines = correspondingFile.readLines().toMutableList()

            // add an import if needed
            val import = "import ${context.outermostDeclaration.qualifiedName()}"
            if (import !in lines)
                lines.add(
                    index = lines.indexOfFirst { it.startsWith("import ") },
                    element = import
                )

            // add the requirement
            lines.add(index = lines.indexOf("}"), element = "$declHeader$declBody")
            lines.add(index = lines.lastIndexOf("}"), element = "$declHeader$outerDeclBody")

            val newText = lines.joinToString("\n")
            correspondingFile.writeText(newText)
        }

    Log.require(wronglyAnnotatedFragmentKSClasses.none(), wronglyAnnotatedFragmentKSClasses){
        "For each android fragment below, " +
        "its arg `context` in ${Names.Nodes} must be a subclass of `AppCompatActivity` or `Fragment`."
    }
}