package com.oneplace.dgapiserver.domain.auth.application

import com.oneplace.dgapiserver.domain.auth.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.auth.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.user.persistance.User
import com.oneplace.dgapiserver.global.jwt.dto.TokenDto
import org.springframework.stereotype.Component

@Component
class AuthMapper {

    fun map(tokenDto: TokenDto) =
        AuthTokenDto(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken
        )

    fun login(tokenDto: AuthTokenDto, user: User) =
        AuthLoginDto(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken
        )

}
