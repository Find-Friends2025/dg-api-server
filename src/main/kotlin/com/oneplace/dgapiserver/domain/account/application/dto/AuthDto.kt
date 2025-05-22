package com.oneplace.dgapiserver.domain.account.application.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class RegisterReqDto(
    @NotBlank
    val uid: String,
    @NotBlank
    val gender: String = "",
    @NotNull
    val birth: LocalDate,
    @NotBlank
    val location: String = "",
    @NotBlank
    val nickname: String = "",
    @NotBlank
    val profilePicUrl: String = ""
)

data class AuthLoginDto(
    val accessToken: String,
    val refreshToken: String
)

data class AuthTokenDto(
    val accessToken: String,
    val refreshToken: String
)
