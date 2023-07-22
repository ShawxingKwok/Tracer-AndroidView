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
    override fun process(times: Int): List<KSAnnotated> {
        if (times == 1) process()
        return emptyList()
    }
}

// add 'override val xx get() = requireActivity()/requireParentFragment()' in 'XxFragmentTracer'
private fun process(){
    // return if error just occurred in tracer
    if (!Tags.interfacesBuilt) return

    val (activityType, fragmentType) =
        arrayOf(
            "androidx.appcompat.app.AppCompatActivity",
            "androidx.fragment.app.Fragment",
        )
        .map { resolver.getClassDeclarationByName(it)!!.asStarProjectedType() }

    val wronglyAnnotatedFragmentKlasses = mutableListOf<KSClassDeclaration>()

    resolver.getAnnotatedSymbols<Tracer.Nodes, KSClassDeclaration>()
        .filter { fragmentType.isAssignableFrom(it.asStarProjectedType()) }
        .mapNotNull { klass ->
            val context = klass.context!!
            val contextType = context.asStarProjectedType()
            when {
                activityType.isAssignableFrom(contextType) -> Triple(klass, context, "requireActivity()")
                fragmentType.isAssignableFrom(contextType) -> Triple(klass, context, "requireParentFragment()")
                else -> {
                    wronglyAnnotatedFragmentKlasses += klass
                    null
                }
            }
        }
        .forEach { (klass, context, requirement) ->
            val declHeader = "    override val `__${getRootNodesName(context)}` get() = "

            val cast = buildString {
                append(context.noPackageName()!!)
                if (context.typeParameters.any()){
                    append("<")
                    append(context.typeParameters.joinToString(", "){ "*" })
                    append(">")
                }
            }

            val declBody = "`_${getRootNodesName(klass)}`.$requirement as $cast"
            val outerDeclBody = declBody.replaceFirst("`", "`_")

            val pathEnding = klass
                .packageName().replace(".", "/")
                .updateIf({ it.any() }){ it.plus("/") }
                .plus("${klass.noPackageName()}${Names.Tracer}s.kt")

            val correspondingFile = Environment.codeGenerator.generatedFile.first { it.path.endsWith(pathEnding) }

            val lines = correspondingFile.readLines().toMutableList()

            // add an import if needed
            val import = "import ${context.outermostDecl.qualifiedName()}"
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

    Log.require(wronglyAnnotatedFragmentKlasses.none(), wronglyAnnotatedFragmentKlasses){
        "For each android fragment below, " +
        "its arg `context` in ${Names.Nodes} must be a subclass of `AppCompatActivity` or `Fragment`".
    }
}