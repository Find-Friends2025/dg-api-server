package com.oneplace.dgapiserver.domain.account.domain.repository

import com.oneplace.dgapiserver.domain.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun existsByUid(uid: String): Boolean
}
