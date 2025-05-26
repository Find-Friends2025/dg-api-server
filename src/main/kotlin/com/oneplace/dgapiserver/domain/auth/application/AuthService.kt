package com.oneplace.dgapiserver.domain.auth.application

import com.oneplace.dgapiserver.domain.auth.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.auth.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.auth.application.dto.RegisterReqDto

interface AuthService {
    fun login(idToken: String): AuthLoginDto
    fun refresh(token: String): AuthTokenDto
    fun register(registerRequest: RegisterReqDto): AuthTokenDto
}
