package pers.shawxingkwok.appsample

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import pers.shawxingkwok.tracer.Tracer

@Tracer.Root
class MainActivity : AppCompatActivity(), MainActivityTracer {

    override val _MainActivity: MainActivity = this
}

@Tracer.Nodes(MainActivity::class)
class HomeFragment : Fragment(), HomeFragmentTracer {

    override val _HomeFragment: HomeFragment get() = this
}

@Tracer.Nodes(HomeFragment::class)
class ChildFragment : Fragment(), ChildFragmentTracer {

    override val _ChildFragment: ChildFragment get() = this
}