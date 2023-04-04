package testbase

import androidx.appcompat.app.AppCompatActivity
import pers.apollokwok.tracer.common.annotations.Tracer
import pers.apollokwok.tracer.common.generated.BasicActivityTracer

@Tracer.Root
abstract class BasicActivity : AppCompatActivity(), BasicActivityTracer {
    val repo = Repo()
    var c = 's'
}

class Repo {
    val a = 1

}