package com.william.sample

import androidx.fragment.app.Fragment
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.LoginFragmentTracer

@Tracer.Nodes(MyFragment::class)
class LoginFragment : Fragment(), LoginFragmentTracer {
    override val _LoginFragment = this
}