package com.oneplace.dgapiserver.global.config

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider {

    private val secret = "super-secret-key"

    fun generateToken(accountId: Long, phoneNumber: String): String {
        val claims = Jwts.claims().setSubject(phoneNumber)
        claims["accountId"] = accountId

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(SignatureAlgorithm.HS256, secret.toByteArray())
            .compact()
    }
}
