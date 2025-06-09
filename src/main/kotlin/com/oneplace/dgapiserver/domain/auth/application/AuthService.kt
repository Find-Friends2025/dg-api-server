package com.oneplace.dgapiserver.domain.auth.application

import com.oneplace.dgapiserver.domain.auth.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.auth.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.auth.application.dto.RegisterReqDto
import com.oneplace.dgapiserver.domain.auth.application.dto.TokenVerifyDto

interface AuthService {
    fun login(idToken: String): AuthLoginDto
    fun refresh(token: String): AuthTokenDto
    fun register(idToken: String, request: RegisterReqDto): AuthTokenDto
    fun verify(token: TokenVerifyDto): String
}
