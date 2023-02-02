
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.ATracer

@Tracer.Root
class A : ATracer {
    override val _A = this
    val z = 2
}

interface L<out T: Any>

fun main() {
    lateinit var l: L<*>
    val ll: L<Any> = l
}