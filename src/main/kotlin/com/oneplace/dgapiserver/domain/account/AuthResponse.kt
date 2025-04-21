package com.oneplace.dgapiserver.domain.account

data class AuthResponse(
    val isNewUser: Boolean,
    val token: String?, // 기존 유저면 JWT, 신규 유저면 null
    val nickname: String? = null
)
