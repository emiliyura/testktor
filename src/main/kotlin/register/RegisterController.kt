package register

import database.users.UserDTO
import database.users.Users
import database.tokens.TokenDTO
import database.tokens.Tokens
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class RegisterController(private val call: ApplicationCall) {
    suspend fun registerNewUser() {
        val receive = call.receive<RegisterRemote>()
        if (!receive.email.contains("@")) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            return
        }

        val existingUser = Users.fetchUser(receive.login)
        if (existingUser != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
            return
        }

        try {
            Users.insert(
                UserDTO(
                    login = receive.login,
                    password = receive.password,
                    email = receive.email,
                    username = receive.login // Используем login как username для примера
                )
            )

            val token = UUID.randomUUID().toString()
            Tokens.insert(
                TokenDTO(
                    rowId = UUID.randomUUID().toString(),
                    login = receive.login,
                    token = token
                )
            )

            call.respond(RegisterResponseRemote(token = token))
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, "Registration failed: ${e.message}")
        }
    }
}