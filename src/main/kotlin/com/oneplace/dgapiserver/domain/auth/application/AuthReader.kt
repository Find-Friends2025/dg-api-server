package com.oneplace.dgapiserver.domain.auth.application

import com.oneplace.dgapiserver.domain.auth.exception.RefreshNotFoundException
import com.oneplace.dgapiserver.domain.auth.persistance.RefreshTokenRepository
import org.springframework.stereotype.Component

@Component
class AuthReader(
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun read(token: String) =
        refreshTokenRepository.findByToken(token)
            ?: throw RefreshNotFoundException()

}
