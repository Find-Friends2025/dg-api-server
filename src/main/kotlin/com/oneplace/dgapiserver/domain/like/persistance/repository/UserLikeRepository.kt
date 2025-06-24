package com.oneplace.dgapiserver.domain.like.persistance.repository

import com.oneplace.dgapiserver.domain.like.persistance.UserLike
import com.oneplace.dgapiserver.domain.user.persistance.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserLikeRepository : JpaRepository<UserLike, Long> {
    fun existsByFromUserAndToUser(fromUser: User, toUser: User): Boolean
    fun deleteByFromUserAndToUser(fromUser: User, toUser: User): Long
    fun countByToUser(toUser: User): Long
    fun findAllByToUser(toUser: User): List<UserLike>
    fun findAllByFromUser(fromUser: User): List<UserLike>
    fun findAllByFromUser(fromUserId: Long): List<UserLike>
}
