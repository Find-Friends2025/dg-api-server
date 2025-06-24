package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.common.util.DateUtil
import com.oneplace.dgapiserver.domain.user.presentation.dto.response.MatchedUserResponse
import com.oneplace.dgapiserver.domain.user.presentation.dto.response.UserProfileResponse
import com.oneplace.dgapiserver.domain.user.persistance.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun mapToUserProfile(user: User, isLiked: Boolean = false): UserProfileResponse {
        return UserProfileResponse(
            id = user.id,
            profilePicUrl = user.profilePicUrl,
            nickname = user.nickname,
            age = DateUtil.birthToAge(user.birth),
            residence = user.location,
            height = user.height,
            bodyType = user.bodyType,
            introduce = user.introduce,
            isOnline = user.isOnline,
            isLiked = isLiked // TODO LIKE 추가 후 수정
        )
    }

    fun mapToUserList(users: List<User>, likesUser: Set<User>): List<MatchedUserResponse> {
        return users.map {
            MatchedUserResponse(
                id = it.id,
                profilePicUrl = it.profilePicUrl,
                nickname = it.nickname,
                age = DateUtil.birthToAge(it.birth),
                residence = it.location,
                isOnline = it.isOnline,
                isLiked = likesUser.contains(it)
            )
        }
    }
}