package com.oneplace.dgapiserver.domain.user.presentation.dto.response

data class UserProfileResponse(
    val id: Long,
    val profilePicUrl: String?,
    val nickname: String?,
    val age: Int?,
    val residence: String?,
    val height: Int?,
    val bodyType: String?,
    val introduce: String?,
    val isOnline: Boolean,
    val isLiked: Boolean
)
