import androidx.fragment.app.Fragment
import pers.apollokwok.appsample.KL
import pers.apollokwok.tracer.common.annotations.Tracer

class K {
    @Tracer.Nodes(KL.HomeFragment::class)
    class ChildFragment : Fragment(), K_ChildFragmentTracer {

        override val `_K․ChildFragment`: ChildFragment get() = this
    }
}