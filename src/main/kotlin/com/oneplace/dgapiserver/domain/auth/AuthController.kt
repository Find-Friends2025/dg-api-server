package com.oneplace.dgapiserver.domain.auth

import com.oneplace.dgapiserver.domain.account.AuthResponse
import com.oneplace.dgapiserver.domain.account.TokenRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val firebaseAuthService: FirebaseAuthService
) {

    @PostMapping("/verify-token")
    fun verifyToken(@RequestBody request: TokenRequest): ResponseEntity<AuthResponse> {
        val response = firebaseAuthService.verifyIdToken(request.idToken)
        return ResponseEntity.ok(response)
    }
}

