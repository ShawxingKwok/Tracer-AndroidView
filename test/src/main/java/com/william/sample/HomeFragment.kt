package com.william.sample

import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.HomeFragmentTracer

@Tracer.Nodes(MainActivity::class)
class HomeFragment : Fragment(), HomeFragmentTracer {
    override val _HomeFragment = this
}