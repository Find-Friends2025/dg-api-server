package com.oneplace.dgapiserver.domain.like.application

import com.oneplace.dgapiserver.domain.like.application.dto.response.WhoLikedMeResponse

interface UserLikeService {
    fun like(toUserId: Long)
    fun unlike(toUserId: Long)
    fun isLiked(toUserId: Long): Boolean
    fun countLikes(toUserId: Long): Long
    fun getUsersWhoLikedMe(): List<WhoLikedMeResponse>

}
