package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.persistance.RefreshToken
import com.oneplace.dgapiserver.domain.account.persistance.RefreshTokenRepository
import com.oneplace.dgapiserver.global.jwt.JwtProperties
import org.springframework.stereotype.Component

@Component
class AuthProcessor(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun saveRefreshToken(userId: Long, token: String) {
        val refreshToken = RefreshToken(
            userId = userId,
            token = token,
            expirationTime = jwtProperties.refreshExp.toInt()
        )
        refreshTokenRepository.save(refreshToken)
    }

}
