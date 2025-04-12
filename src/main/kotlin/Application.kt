import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import login.LoginController
import login.configureLoginRouting
import register.RegisterController
import register.configureRegisterRouting
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import database.users.Users
import database.tokens.Tokens
import login.configureLoginRouting

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureDatabase()
    configureSerialization()
    configureRouting()
}

fun Application.configureRouting() {
    routing {
        configureLoginRouting()
        configureRegisterRouting()

        post("/login") {
            LoginController(call).performLogin()
        }
        post("/register") {
            RegisterController(call).registerNewUser()
        }
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

fun Application.configureDatabase() {
    println("Connecting to PostgreSQL...")
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/testktorsql",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "1234" // Исправлен пароль
    )
    println("Connected successfully!")

    transaction {
        println("Creating tables...")
        SchemaUtils.create(Users, Tokens)
        println("Tables created: ${Users.tableName}, ${Tokens.tableName}")
    }


}

