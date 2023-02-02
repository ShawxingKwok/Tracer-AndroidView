package com.william.sample

import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.ChildFragmentTracer
import pers.apollokwok.tracer.common.generated.MyFragmentTracer
import pers.apollokwok.tracer.common.generated.SubChildFragmentTracer
import testbase.BasicFragment

@Tracer.Nodes(MainActivity::class)
class MyFragment<T> : BasicFragment(), MyFragmentTracer {
    override val _MyFragment: MyFragment<*> get() = this
    val afa = 1
}

@Tracer.Nodes(MyFragment::class)
class ChildFragment<T: Iterable<*>> : BasicFragment(), ChildFragmentTracer {
    override val _ChildFragment: ChildFragment<*> get() = this
    val zsd: Char = 'd'
    val zsdfa: Char = 'd'
}

@Tracer.Nodes(ChildFragment::class)
class SubChildFragment : BasicFragment(), SubChildFragmentTracer {
    override val _SubChildFragment: SubChildFragment get() = this
}