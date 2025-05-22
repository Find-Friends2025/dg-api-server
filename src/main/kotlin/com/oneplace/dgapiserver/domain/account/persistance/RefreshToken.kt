package com.oneplace.dgapiserver.domain.account.persistance

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

@RedisHash("refresh_token")
data class RefreshToken(
    @Id
    val userId: Long,

    @Indexed
    val token: String,

    @TimeToLive
    val expirationTime: Int
)
