package com.william.sample

import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.MallTracer

@Tracer.Root
class Mall : MallTracer {
    override val _Mall = this

}