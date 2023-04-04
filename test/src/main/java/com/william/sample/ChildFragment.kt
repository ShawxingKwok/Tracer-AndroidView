package com.william.sample

import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.ChildFragmentTracer

@Tracer.Nodes(HomeFragment::class)
class ChildFragment : Fragment(), ChildFragmentTracer {


    override val _ChildFragment: ChildFragment = this
}