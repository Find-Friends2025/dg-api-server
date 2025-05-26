package com.oneplace.dgapiserver.domain.auth.persistance

import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, Long> {
    fun findByToken(token: String): RefreshToken?
}
