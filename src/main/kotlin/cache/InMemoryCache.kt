//package cache
//
//import java.util.UUID
//
//object InMemoryCache {
//    private val users = mutableMapOf<String, Pair<String, String>>() // login -> (password, token)
//
//    fun register(login: String, password: String, email: String): String? {
//        // Проверка, зарегистрирован ли пользователь ранее
//        if (users.containsKey(login)) {
//            return null // Пользователь уже существует
//        }
//        val token = generateToken(login)
//        users[login] = Pair(password, token)
//        return token
//    }
//
//    // Остальной код (login и generateToken)
//    fun login(login: String, password: String): String? {
//        val user = users[login] ?: return null
//        return if (user.first == password) user.second else null
//    }
//
//    private fun generateToken(login: String): String {
//        return "token_${System.currentTimeMillis()}_${UUID.randomUUID().toString()}"
//    }
//}