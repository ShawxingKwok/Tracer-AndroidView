package com.william.sample

import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.FJAOPChildFragmentTracer

class FJAOP {
    @Tracer.Nodes(MyFragment::class)
    class ChildFragment<T> : Fragment(), FJAOPChildFragmentTracer {
        val afja = 2
        override val _FJAOPChildFragment: ChildFragment<*> get() = this
    }
}