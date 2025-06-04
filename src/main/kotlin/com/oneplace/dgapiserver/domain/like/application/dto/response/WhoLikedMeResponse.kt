package com.oneplace.dgapiserver.domain.like.application.dto.response

data class WhoLikedMeResponse(
    val id: Long,
    val profilePicUrl: String?,
    val nickname: String?,
    val age: Int?,
    val location: String,
    val isLiked: Boolean
) {
}
