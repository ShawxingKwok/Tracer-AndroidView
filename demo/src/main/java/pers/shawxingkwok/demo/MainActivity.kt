package pers.shawxingkwok.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import pers.shawxingkwok.tracer.Tracer

@Tracer.Tip
class SharedViewModel : ViewModel()

@Tracer.Tip
class MainViewModel : ViewModel()

class ChildViewModel : ViewModel()

@Tracer.Root
class MainActivity : AppCompatActivity(), MainActivityTracer {
    val sharedVm: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override val _MainActivity: MainActivity get() = this
}

@Tracer.Nodes(MainActivity::class)
class MainFragment : Fragment(R.layout.fragment_main), MainFragmentTracer {
    companion object {
        fun newInstance() = MainFragment()
    }

    val vm: MainViewModel by viewModels()

    private val sharedVm get() = `__SharedViewModel_˚MainActivity_MainActivity_sharedVm`

    override val _MainFragment: MainFragment get() = this
}

@Tracer.Nodes(MainFragment::class)
class ChildFragment : Fragment(R.layout.fragment_child), ChildFragmentTracer{
    private val sharedVm get() = `__SharedViewModel_˚MainActivity_MainActivity_sharedVm`
    private val mainVm get() = `__MainViewModel_˚MainFragment_MainFragment_vm`

    private val vm: ChildViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TRACER", "$sharedVm, $mainVm, $vm")
    }

    override val _ChildFragment: ChildFragment get() = this
}