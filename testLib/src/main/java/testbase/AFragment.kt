package testbase

import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.AFragmentTracer
import pers.apollokwok.tracer.common.generated.BFragmentTracer

@Tracer.Nodes(BasicActivity::class)
internal class AFragment : Fragment(), AFragmentTracer {
    override val _AFragment: AFragment get() = this
}

@Tracer.Nodes(AFragment::class)
internal class BFragment : Fragment(), BFragmentTracer {
    override val _BFragment: BFragment get() = this

}