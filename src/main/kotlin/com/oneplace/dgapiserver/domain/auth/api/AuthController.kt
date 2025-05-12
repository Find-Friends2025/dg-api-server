package com.oneplace.dgapiserver.domain.auth.api

import com.oneplace.dgapiserver.domain.account.application.AccountService
import com.oneplace.dgapiserver.domain.auth.api.dto.request.RegisterRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val accountService: AccountService
) {

    @PostMapping("/login")
    fun login(@RequestHeader("Authorization") authHeader: String): ResponseEntity<Any> {
        return accountService.handleFirebaseLogin(authHeader)
    }

    @PostMapping("/register")
    fun signup(@RequestBody registerRequest: RegisterRequest): ResponseEntity<Any> {
        return accountService.registerUser(registerRequest)
    }
}
