package testbase

import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.AFragmentTracer

@Tracer.Nodes(BasicActivity::class)
internal class AFragment : Fragment(), AFragmentTracer{


    override val _AFragment: AFragment get() = this
}