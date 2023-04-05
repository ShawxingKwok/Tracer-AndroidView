package pers.apollokwok.appsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.ChildFragmentTracer
import pers.apollokwok.tracer.common.generated.HomeFragmentTracer
import pers.apollokwok.tracer.common.generated.MainActivityTracer

@Tracer.Root
class MainActivity : AppCompatActivity(), MainActivityTracer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override val _MainActivity: MainActivity = this
}

@Tracer.Nodes(MainActivity::class)
class HomeFragment : Fragment(), HomeFragmentTracer {

    override val _HomeFragment: HomeFragment = this
    override val __MainActivity get() = requireActivity() as MainActivity
}

@Tracer.Nodes(HomeFragment::class)
class ChildFragment : Fragment(), ChildFragmentTracer{

    override val _ChildFragment: ChildFragment = this
    override val __HomeFragment get() = requireParentFragment() as HomeFragment
}