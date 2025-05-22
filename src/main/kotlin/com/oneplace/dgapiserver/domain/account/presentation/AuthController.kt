package com.oneplace.dgapiserver.domain.account.presentation

import com.oneplace.dgapiserver.domain.account.application.AuthService
import com.oneplace.dgapiserver.domain.account.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.account.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.account.application.dto.RegisterReqDto
import com.oneplace.dgapiserver.global.response.BaseResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

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
