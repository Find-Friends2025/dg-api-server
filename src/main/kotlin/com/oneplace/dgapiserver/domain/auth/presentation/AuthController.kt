package com.oneplace.dgapiserver.domain.auth.presentation

import com.oneplace.dgapiserver.domain.auth.application.AuthService
import com.oneplace.dgapiserver.domain.auth.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.auth.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.auth.application.dto.RegisterReqDto
import com.oneplace.dgapiserver.global.response.BaseResponse
import org.springframework.web.bind.annotation.*

//@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

//    @Operation(summary = "Register a user")
    @PostMapping("/login")
    fun login(
        @RequestHeader("Authorization") authHeader: String
    ): BaseResponse<AuthLoginDto> {
        return BaseResponse(
            message = "Login Success",
            data = authService.login(authHeader)
        )
    }

    @PostMapping("/register")
    fun signup(
        @RequestBody registerRequest: RegisterReqDto
    ): BaseResponse<AuthTokenDto> {
        return BaseResponse(
            message = "Registration Success",
            data = authService.register(registerRequest)
        )
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestHeader("Authorization") token: String
    ): BaseResponse<AuthTokenDto> {
        return BaseResponse(
            message = "Refresh Request Success",
            data = authService.refresh(token)
        )
    }
}
