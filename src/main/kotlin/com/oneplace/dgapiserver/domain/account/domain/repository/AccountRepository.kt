package com.oneplace.dgapiserver.domain.account.domain.repository

import com.oneplace.dgapiserver.domain.user.domain.Account
import com.oneplace.dgapiserver.domain.account.exception.UserNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

interface AccountRepository : JpaRepository<Account, Long> {
    fun existsByUid(uid: String): Boolean

    fun findByIdOrThrow(id: Long): Account{
        return findByIdOrNull(id) ?: throw UserNotFoundException()
    }
}
