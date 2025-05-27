package com.oneplace.dgapiserver.global.security

import com.oneplace.dgapiserver.domain.user.application.UserReader
import com.oneplace.dgapiserver.domain.user.persistance.User
import com.oneplace.dgapiserver.global.jwt.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import java.nio.charset.StandardCharsets

@Component
@RequiredArgsConstructor
class TokenExtractor(
    private val jwtProperties: JwtProperties,
    private val userReader: UserReader
) {

    @Transactional
    fun getAuthentication(accessToken: String?): Authentication {
        val claims = getClaims(accessToken)
        val user: User = userReader.read(claims.subject.toLong())

        val details = CustomUserDetails(user)

        return UsernamePasswordAuthenticationToken(details, null, details.getAuthorities())
    }


    fun extract(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }


    fun getClaims(token: String?): Claims {
        val keyBytes = jwtProperties.accessSecret.toByteArray(StandardCharsets.UTF_8)
        val key = Keys.hmacShaKeyFor(keyBytes)

        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}