package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.auth.application.UserAuthenticationHolder
import com.oneplace.dgapiserver.domain.like.persistance.repository.UserLikeRepository
import com.oneplace.dgapiserver.domain.user.presentation.dto.request.ModifyUserRequest
import com.oneplace.dgapiserver.domain.user.presentation.dto.request.SearchUserRequest
import com.oneplace.dgapiserver.domain.user.presentation.dto.response.MatchedUserResponse
import com.oneplace.dgapiserver.domain.user.presentation.dto.response.UserProfileResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class UserServiceImpl(
    private val userReader: UserReader,
    private val userMapper: UserMapper,
    private val likeRepository: UserLikeRepository,
    private val userModifier: UserModifier,
    private val userAuthenticationHolder: UserAuthenticationHolder
): UserService {

    @Transactional(readOnly = true)
    override fun searchUser(searchUserRequest: SearchUserRequest): List<MatchedUserResponse> {
        val users = userReader.read(
            age = searchUserRequest.age,
            residence = searchUserRequest.residence,
            height = searchUserRequest.height,
            bodyType = searchUserRequest.bodyType,
            hasIntroduce = searchUserRequest.hasIntroduce,
            isNewUser = searchUserRequest.isNewUser
        )
        val likesUser = likeRepository.findAllByFromUser(userAuthenticationHolder.current())
            .map { it.toUser }
            .toSet()
        return userMapper.mapToUserList(users, likesUser)
    }

    @Transactional(readOnly = true)
    override fun getUserDetail(userId: Long): UserProfileResponse {
        val user = userReader.read(userId)
        return userMapper.mapToUserProfile(user)
    }

    @Transactional(readOnly = true)
    override fun getMyProfile(): UserProfileResponse =
        userMapper.mapToUserProfile(userAuthenticationHolder.current())

    override fun modifyProfile(request: ModifyUserRequest) {
        val user = userAuthenticationHolder.current()
        userModifier.modify(
            user= user,
            birth = request.birth,
            nickname = request.nickname,
            residence = request.residence,
            isOnline = request.isOnline,
        )
    }
}