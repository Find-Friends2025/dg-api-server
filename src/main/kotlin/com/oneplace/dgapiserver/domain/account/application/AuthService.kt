package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.account.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.account.application.dto.RegisterReqDto

interface AuthService {
    fun login(idToken: String): AuthLoginDto
    fun register(registerRequest: RegisterReqDto): AuthTokenDto
}
