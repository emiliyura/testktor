import com.example.configureSerialization
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import login.configureLoginRouting
import register.configureRegisterRouting

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
