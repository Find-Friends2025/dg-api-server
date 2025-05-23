package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.exception.RefreshNotFoundException
import com.oneplace.dgapiserver.domain.account.persistance.RefreshTokenRepository
import org.springframework.stereotype.Component

@Component
class AuthReader(
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun read(token: String) =
        refreshTokenRepository.findByToken(token)
            ?: throw RefreshNotFoundException()

}
