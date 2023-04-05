package pers.apollokwok.appsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.FirstFragmentTracer
import pers.apollokwok.tracer.common.generated.MainActivityTracer

@Tracer.Root
class MainActivity : AppCompatActivity(), MainActivityTracer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val _MainActivity: MainActivity = this
}

@Tracer.Nodes(MainActivity::class)
class FirstFragment : Fragment(), FirstFragmentTracer {
    override val _FirstFragment: FirstFragment = this
    override val __MainActivity get() = requireActivity() as MainActivity
}