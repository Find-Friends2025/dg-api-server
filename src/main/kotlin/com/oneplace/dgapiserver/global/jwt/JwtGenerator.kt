package com.oneplace.dgapiserver.global.jwt

import com.oneplace.dgapiserver.domain.user.persistance.Authority
import com.oneplace.dgapiserver.global.jwt.dto.TokenDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.util.*

@Component
class JwtGenerator(
    private val jwtProperties: JwtProperties
) {

    companion object {
        const val AUTHORITY = "authority"
        const val TOKEN_TYPE = "tokenType"
        const val ACCESS_TOKEN = "accessToken"
        const val REFRESH_TOKEN = "refreshToken"
    }

    fun generateToken(userId: String, authority: Authority): TokenDto =
        TokenDto(
            accessToken = generateAccessToken(userId, authority),
            refreshToken = generateRefreshToken(userId),
            accessTokenExp = LocalDateTime.now().plusSeconds(jwtProperties.accessExp),
            refreshTokenExp = LocalDateTime.now().plusSeconds(jwtProperties.refreshExp)
        )

    private fun generateAccessToken(userId: String, authority: Authority): String =
        Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(jwtProperties.accessSecret.toByteArray(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
            .setSubject(userId)
            .claim(TOKEN_TYPE, ACCESS_TOKEN)
            .claim(AUTHORITY, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExp * 1000))
            .compact()

    private fun generateRefreshToken(userId: String): String =
        Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(jwtProperties.refreshSecret.toByteArray(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
            .setSubject(userId)
            .claim(TOKEN_TYPE, REFRESH_TOKEN)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExp * 1000))
            .compact()

}
