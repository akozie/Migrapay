package com.seamfix.migrapay

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

data class User(val username: String, val password: String, val email: String)

class UserRepository {
    private val users = mutableListOf<User>()

    fun addUser(user: User) {
        users.add(user)
    }

    fun findUserByUsername(username: String): User? {
        return users.find { it.username == username }
    }
}

class UserService(private val userRepository: UserRepository) {

    fun registerUser(username: String, password: String, email: String): String {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return "All fields are required."
        }

        if (!isValidEmail(email)) {
            return "Invalid email address."
        }

        if (userRepository.findUserByUsername(username) != null) {
            return "Username already exists."
        }

        val hashedPassword = hashPassword(password)
        val user = User(username, hashedPassword, email)
        userRepository.addUser(user)

        return "User registered successfully."
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"
        val pattern = Pattern.compile(emailRegex)
        return pattern.matcher(email).matches()
    }


    private fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val hash = md.digest(password.toByteArray())
        return hash.fold("", { str, it -> str + "%02x".format(it) })
    }
}

fun main() {
    val userRepository = UserRepository()
    val userService = ø UserService (userRepository)

    val result = userService.registerUser("john_doe", "password123", "john.doe@example.com")

    println(result)
}

