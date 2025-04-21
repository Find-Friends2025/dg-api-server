package com.oneplace.dgapiserver.domain.auth

import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository
) : AccountService {
    override fun existsByUid(uid: String): Boolean {
        return accountRepository.existsByUid(uid)
    }
}

