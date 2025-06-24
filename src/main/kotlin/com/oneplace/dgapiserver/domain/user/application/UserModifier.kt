package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.user.persistance.User
import com.oneplace.dgapiserver.domain.user.persistance.repository.UserRepository
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UserModifier(
    private val userRepository: UserRepository
) {
    fun modify(
        user: User,
        birth: LocalDate?,
        nickname: String?,
        introduce: String?,
        gender: String?,
        profilePicUrl: String?,
        residence: String?,
        isOnline: Boolean?
    ){
        user.modify(
            birth = birth,
            nickname = nickname,
            introduce = introduce,
            gender = gender,
            profilePicUrl = profilePicUrl,
            residence = residence,
            isOnline = isOnline
        )
        userRepository.save(user)
    }
}
