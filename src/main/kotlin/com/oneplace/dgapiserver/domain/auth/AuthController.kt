package com.oneplace.dgapiserver.domain.auth

import com.google.firebase.auth.FirebaseToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val firebaseTokenVerifier: FirebaseTokenVerifier,
    private val accountService: AccountService
) {

    @PostMapping("/login")
    fun login(@RequestHeader("Authorization") authHeader: String): ResponseEntity<Any> {
        val token = authHeader.removePrefix("Bearer ").trim()

        val decodedToken: FirebaseToken = try {
            firebaseTokenVerifier.verifyIdToken(token)
        } catch (e: Exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Firebase token")
        }

        val uid = decodedToken.uid

        return if (accountService.existsByUid(uid)) {
            ResponseEntity.ok().body(mapOf("message" to "Login Success"))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("message" to "User Not Found", "uid" to uid))
        }
    }
}
