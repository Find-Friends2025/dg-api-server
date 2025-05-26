package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.auth.exception.UserNotFoundException
import com.oneplace.dgapiserver.domain.user.persistance.User
import com.oneplace.dgapiserver.domain.user.persistance.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository
) {

    fun readByUidOrNull(uid: String): User? =
        userRepository.findByUid(uid)

    fun read(userId: Long) =
        userRepository.findByIdOrNull(userId)
            ?: throw UserNotFoundException()
}
