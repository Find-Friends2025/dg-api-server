package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.user.presentation.dto.request.ModifyUserRequest
import com.oneplace.dgapiserver.domain.user.presentation.dto.request.SearchUserRequest
import com.oneplace.dgapiserver.domain.user.presentation.dto.response.MatchedUserResponse
import com.oneplace.dgapiserver.domain.user.presentation.dto.response.UserProfileResponse

interface UserService {
    fun searchUser(searchUserRequest: SearchUserRequest): List<MatchedUserResponse>
    fun getUserDetail(userId: Long): UserProfileResponse
    fun getMyProfile(): UserProfileResponse
    fun modifyProfile(request: ModifyUserRequest)
}