package login

import cache.InMemoryCache
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val loginData = call.receive<LoginRemote>()
            val token = InMemoryCache.login(loginData.login, loginData.password)
            if (token == null) {
                call.respondText("User not found or invalid password")
            } else {
                call.respondText("Token: $token")
            }
        }
    }
}