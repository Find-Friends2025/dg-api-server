package com.oneplace.dgapiserver.domain.auth

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<AccountEntity, Long> {
    fun existsByUid(uid: String): Boolean
}
