import Test
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        get("/test") {
            call.respond(Test("Привет от класса тест"))
        }
    }
}

// call.respondText(Test("Привет от класса тест").toString())
//            call.respondText("Проверка запроса на postman")