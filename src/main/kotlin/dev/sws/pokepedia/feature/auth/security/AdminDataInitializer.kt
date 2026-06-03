package dev.sws.pokepedia.feature.auth.security

import dev.sws.pokepedia.feature.auth.entity.Admin
import dev.sws.pokepedia.feature.auth.repository.AdminRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AdminDataInitializer(
    private val adminRepository: AdminRepository,
    private val passwordEncoder: PasswordEncoder,
    @Value("\${app.admin.username}") private val defaultUsername: String,
    @Value("\${app.admin.password}") private val defaultPassword: String,
) : CommandLineRunner {

    override fun run(vararg args: String) {
        if (adminRepository.count() == 0L) {

            val hashedPassword = passwordEncoder.encode(defaultPassword)

            val defaultAdmin = Admin(
                username = defaultUsername,
                password = hashedPassword.orEmpty()
            )

            adminRepository.save(defaultAdmin)
            println("✅ Default Admin User created successfully.")
        } else {
            println("✅ Admin User already exists in database. Skipping initialization.")
        }
    }
}