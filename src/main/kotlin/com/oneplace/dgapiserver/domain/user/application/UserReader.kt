package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.auth.exception.UserNotFoundException
import com.oneplace.dgapiserver.domain.like.persistance.repository.UserLikeRepository
import com.oneplace.dgapiserver.domain.user.persistance.User
import com.oneplace.dgapiserver.domain.user.persistance.repository.UserRepository
import com.oneplace.dgapiserver.domain.user.persistance.repository.UserSpecification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: UserRepository,
    private val userLikeRepository: UserLikeRepository
) {

    fun readByUidOrNull(uid: String): User? =
        userRepository.findByUid(uid)

    fun read(userId: Long) =
        userRepository.findByIdOrNull(userId)
            ?: throw UserNotFoundException()

    fun read(
        age: Int?,
        residence: String?,
        height: Int?,
        bodyType: String?,
        hasIntroduce: Boolean?,
        isNewUser: Boolean?,
    ): List<User> {
        return userRepository.findAll(UserSpecification.search(
            age = age,
            residence = residence,
            height = height,
            bodyType = bodyType,
            hasIntroduce = hasIntroduce,
            isNewUser = isNewUser,
        ))
    }
}
