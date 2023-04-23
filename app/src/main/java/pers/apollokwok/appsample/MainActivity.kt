package pers.apollokwok.appsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer

@Tracer.Root
class MainActivity : AppCompatActivity(), MainActivityTracer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val _MainActivity: MainActivity = this
}

class KL {
    @Tracer.Nodes(MainActivity::class)
    class HomeFragment<T> : Fragment(), KL_HomeFragmentTracer {

        override val `_KL․HomeFragment‹↓T-Any？›`: HomeFragment<*> get() = this
    }
}