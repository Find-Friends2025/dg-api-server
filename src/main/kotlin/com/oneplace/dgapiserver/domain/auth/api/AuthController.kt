package com.oneplace.dgapiserver.domain.auth.api

import com.oneplace.dgapiserver.domain.account.application.AccountService
import com.oneplace.dgapiserver.domain.auth.api.dto.request.RegisterRequest
import com.oneplace.dgapiserver.global.response.BaseResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val accountService: AccountService
) {

    @PostMapping("/login")
    fun login(
        @RequestHeader("Authorization") authHeader: String
    ): BaseResponse<Unit> {
        return BaseResponse(
            message = "Login Success",
            data = accountService.handleFirebaseLogin(authHeader)
        )
    }

    @PostMapping("/register")
    fun signup(
        @RequestBody registerRequest: RegisterRequest
    ): BaseResponse<Unit> {
        return BaseResponse(
            message = "Registration Success",
            data = accountService.registerUser(registerRequest)
        )
    }
}