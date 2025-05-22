package com.oneplace.dgapiserver.global.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    val accessSecret: String,
    val refreshSecret: String,
    val accessExp: Long,
    val refreshExp: Long
)
