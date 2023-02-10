package testbase

import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer

class KK {
    @Tracer.Nodes(BasicActivity::class)
    class YFragment : Fragment()
}