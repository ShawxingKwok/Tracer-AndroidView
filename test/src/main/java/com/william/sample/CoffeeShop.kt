package com.william.sample

import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.CoffeeShopTracer

@Tracer.Nodes(Mall::class)
class CoffeeShop(override val __Mall: Mall) : CoffeeShopTracer {
    override val _CoffeeShop = this
}