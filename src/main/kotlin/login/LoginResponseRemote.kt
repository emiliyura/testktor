package login

import kotlinx.serialization.Serializable

// Добавьте этот класс в файл LoginRemote.kt или создайте новый файл
@Serializable
data class LoginResponseRemote(val token: String)

