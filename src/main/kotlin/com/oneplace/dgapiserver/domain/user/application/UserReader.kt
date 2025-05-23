package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.account.domain.Account
import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import com.oneplace.dgapiserver.domain.account.exception.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: AccountRepository
) {

    fun readByUidOrNull(uid: String): Account? =
        userRepository.findByUid(uid)

    fun read(userId: Long) =
        userRepository.findByIdOrNull(userId)
            ?: throw UserNotFoundException()
}
