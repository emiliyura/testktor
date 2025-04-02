package register

import cache.InMemoryCache
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import utils.Validators

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerData = call.receive<RegisterRemote>()
            if (!Validators.isValidEmail(registerData.email)) {
                call.respondText("Email is not valid")
                return@post
            }
            val token = InMemoryCache.register(registerData.login, registerData.password, registerData.email)
            if (token == null) {
                call.respondText("User already exists")
            } else {
                call.respondText("Token: $token")
            }
        }
    }
}