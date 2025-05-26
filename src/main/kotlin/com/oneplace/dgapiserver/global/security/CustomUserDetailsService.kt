package com.oneplace.dgapiserver.global.security

import com.oneplace.dgapiserver.domain.auth.exception.UserNotFoundException
import com.oneplace.dgapiserver.domain.user.persistance.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(userId: String): UserDetails =
        CustomUserDetails(
            userRepository.findById(userId.toLong())
                .orElseThrow { UserNotFoundException() }
        )
}
