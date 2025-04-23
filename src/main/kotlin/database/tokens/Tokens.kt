//package database.tokens
//
//import database.users.Users.varchar
//import jdk.javadoc.internal.doclets.formats.html.Table
//import org.jetbrains.exposed.sql.transactions.transaction
//
//object Tokens : Table("tokens") {
//    private val login = varchar("login", 25)
//    private val id = varchar("id", 50)
//    private val token = varchar("token", 50)
//
//    fun insert(tokenDTO: TokenDTO) {
//        transaction {
//            Tokens.insert(
//                it[login] = tokenDTO.login
//                it[id] = tokenDTO.rowId
//                it[token] = tokenDTO.token
//            )
//        }
//    }
//}



package database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("tokens") {
    val login = varchar("login", 25)
    val id = varchar("id", 50)
    val token = varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[login] = tokenDTO.login
                it[id] = tokenDTO.rowId
                it[token] = tokenDTO.token
            }
        }
    }
}


