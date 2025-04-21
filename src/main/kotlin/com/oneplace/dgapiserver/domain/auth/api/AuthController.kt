package com.oneplace.dgapiserver.domain.auth.api

import com.oneplace.dgapiserver.domain.account.application.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val accountService: AccountService
) {

    @PostMapping("/login")
    fun login(@RequestHeader("Authorization") authHeader: String): ResponseEntity<Any> {
        val token = authHeader.removePrefix("Bearer ").trim()
        return accountService.handleFirebaseLogin(token)
    }
}
