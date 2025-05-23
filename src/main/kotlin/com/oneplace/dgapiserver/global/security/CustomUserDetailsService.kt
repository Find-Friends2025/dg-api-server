package com.oneplace.dgapiserver.global.security

import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import com.oneplace.dgapiserver.domain.account.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(userId: String): UserDetails =
        CustomUserDetails(
            accountRepository.findById(userId.toLong())
                .orElseThrow { UserNotFoundException() }
        )
}
