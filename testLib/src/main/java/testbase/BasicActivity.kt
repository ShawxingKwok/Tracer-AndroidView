package testbase

//import tracer.androidExtra.SingleActivity
//import pers.annotations.Tracer
import androidx.appcompat.app.AppCompatActivity
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.BasicActivityTracer

@Tracer.Root
abstract class BasicActivity : AppCompatActivity(), BasicActivityTracer {
//    val `✶☀➞❴↑·❮[` = 2

    // override val _BasicActivity: BasicActivity get() = this
    val repo = Repo()
    var c = 's'
}

class Repo {
    val a = 1
}