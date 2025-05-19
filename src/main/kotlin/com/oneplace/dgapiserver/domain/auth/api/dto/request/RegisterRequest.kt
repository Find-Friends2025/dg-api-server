package com.oneplace.dgapiserver.domain.auth.api.dto.request

import java.time.LocalDate

data class RegisterRequest(
    val uid: String,
    val gender: String = "",
    val birth: LocalDate,
    val location: String = "",
    val nickname: String = "",
    val profilePicUrl: String = ""
)