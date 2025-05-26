package com.oneplace.dgapiserver.domain.user.presentation.dto.response

data class MatchedUserResponse(
    val id: Long,
    val profilePicUrl: String?,
    val nickname: String?,
    val age: Int?,
    val residence: String?,
    val isOnline: Boolean,
    val isLiked: Boolean
) {
}