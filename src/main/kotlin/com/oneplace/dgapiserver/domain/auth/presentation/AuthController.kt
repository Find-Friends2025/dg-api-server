package com.oneplace.dgapiserver.domain.auth.presentation

import com.oneplace.dgapiserver.domain.auth.application.AuthService
import com.oneplace.dgapiserver.domain.auth.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.auth.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.auth.application.dto.RegisterReqDto
import com.oneplace.dgapiserver.domain.auth.application.dto.TokenVerifyDto
import com.oneplace.dgapiserver.domain.auth.exception.ExpiredTokenException
import com.oneplace.dgapiserver.global.response.BaseResponse
import com.oneplace.dgapiserver.global.support.InternalApiKeyProperties
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "Auth")
@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val internalApiKeyProperties: InternalApiKeyProperties
) {

    @Operation(summary = "Register a user")
    @PostMapping("/login")
    fun login(
        @RequestHeader("X-firebase-token") authHeader: String
    ): BaseResponse<AuthLoginDto> {
        return BaseResponse(
            message = "Login Success",
            data = authService.login(authHeader)
        )
    }

    @PostMapping("/register")
    fun signup(
        @RequestHeader("X-firebase-token") authHeader: String,
        @RequestBody registerRequest: RegisterReqDto
    ): BaseResponse<AuthTokenDto> {
        return BaseResponse(
            message = "Registration Success",
            data = authService.register(authHeader, registerRequest)
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

    @PostMapping("/token/verify")
    fun verify(
        @RequestHeader("X-Internal-Api-Key") authHeader: String,
        @RequestBody tokenVerifyDto: TokenVerifyDto
    ): String {
        if (authHeader != internalApiKeyProperties.apiKey) {
            throw ExpiredTokenException()
        }
        return authService.verify(tokenVerifyDto)

    }
}
