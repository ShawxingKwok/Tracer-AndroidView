package testbase

import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.BasicFragmentTracer

@Tracer.Nodes(BasicActivity::class)
abstract class BasicFragment : Fragment(), BasicFragmentTracer {
    val a = 1
    val gji = "Gf"
    val gjifap = 1241
}