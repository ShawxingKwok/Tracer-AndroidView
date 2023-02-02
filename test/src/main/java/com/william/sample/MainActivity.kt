package com.william.sample

import android.os.Bundle
import android.util.Log
import com.snaky.kspsample.R
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.MainActivityTracer
import pers.apollokwok.tracer.common.generated.`_Long？_MainActivity_z`

@Tracer.Root
class MainActivity : testbase.BasicActivity(), MainActivityTracer {
    var z: Long? = 2L

//    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("William", "onCreate: $`_Long？_MainActivity_z`")
        setContentView(R.layout.activity_main)
    }

    override val _MainActivity: MainActivity get() = this
}