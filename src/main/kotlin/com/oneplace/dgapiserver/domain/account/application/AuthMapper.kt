package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.account.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.account.domain.Account
import com.oneplace.dgapiserver.global.jwt.dto.TokenDto
import org.springframework.stereotype.Component

@Component
class AuthMapper {

    fun map(tokenDto: TokenDto) =
        AuthTokenDto(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken
        )

    fun login(tokenDto: AuthTokenDto, user: Account) =
        AuthLoginDto(
            accessToken = tokenDto.accessToken,
            refreshToken = tokenDto.refreshToken
        )

}
