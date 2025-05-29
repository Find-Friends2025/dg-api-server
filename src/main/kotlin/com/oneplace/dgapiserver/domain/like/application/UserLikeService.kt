package com.oneplace.dgapiserver.domain.like.application

interface UserLikeService {
    fun like(fromUserId: Long, toUserId: Long)
    fun unlike(fromUserId: Long, toUserId: Long)
    fun isLiked(fromUserId: Long, toUserId: Long): Boolean
    fun countLikes(toUserId: Long): Long
}
