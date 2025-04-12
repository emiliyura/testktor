//import io.ktor.server.application.*
//import io.ktor.server.routing.*
//import login.LoginController
//import login.configureLoginRouting
//import login.configureLoginRouting
//import register.RegisterController
//import register.configureRegisterRouting
//
//
//fun Application.configureRouting() {
//    // Ensure no other configureRouting is being called or defined here
//    routing {
//        // Call external routing configurations if needed
//        ConfigureLoginRouting()
//        configureRegisterRouting()
//
//        // Define your routes
//        post("/login") {
//            LoginController(call).performLogin()
//        }
//        post("/register") {
//            RegisterController(call).registerNewUser()
//        }
//    }
//}
