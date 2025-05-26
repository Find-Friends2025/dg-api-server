package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.auth.application.dto.RegisterReqDto
import com.oneplace.dgapiserver.domain.user.persistance.User
import com.oneplace.dgapiserver.domain.user.persistance.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserProcessor(
    private val userReader: UserReader,
    private val userRepository: UserRepository
) {

    fun getUserOrCreate(uid: String): User =
        userReader.readByUidOrNull(uid)
            ?: userRepository.save(User.of(uid))

    fun signUp(user: User, dto: RegisterReqDto): User {
        user.signUp(dto)
        return userRepository.save(user)
    }

}
