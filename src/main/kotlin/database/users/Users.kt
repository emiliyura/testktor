package database.users

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

// Импорт UserDTO, если он в другом пакете
import database.users.UserDTO // Если UserDTO в том же пакете, этот импорт может быть опущен

object Users : Table("users") {
    val login = varchar("login", 25)
    val password = varchar("password", 25)
    val username = varchar("username", 30)
    val email = varchar("email", 25).nullable()

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[username] = userDTO.username
                it[email] = userDTO.email
            }
        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            transaction {
                selectAll().where { Users.login eq login }.singleOrNull()?.let { row ->
                    UserDTO(
                        login = row[Users.login],
                        password = row[password],
                        username = row[username],
                        email = row[email]
                    )
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}

