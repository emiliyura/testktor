package register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRemote(val login: String, val password: String, val email: String)