package com.oneplace.dgapiserver.domain.like.application

interface UserLikeService {
    fun like(toUserId: Long)
    fun unlike(toUserId: Long)
    fun isLiked(toUserId: Long): Boolean
    fun countLikes(toUserId: Long): Long
}
