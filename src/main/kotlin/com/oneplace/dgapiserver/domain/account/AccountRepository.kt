package com.oneplace.dgapiserver.domain.account

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByPhoneNumber(phoneNumber: String): Account?
}
