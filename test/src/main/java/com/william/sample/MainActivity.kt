package com.william.sample

import android.os.Bundle
import com.snaky.kspsample.R
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.MainActivityTracer

@Tracer.Root
class MainActivity : testbase.BasicActivity(), MainActivityTracer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val _MainActivity: MainActivity get() = this
}