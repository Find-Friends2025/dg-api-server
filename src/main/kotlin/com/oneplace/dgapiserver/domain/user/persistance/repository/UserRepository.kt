package com.oneplace.dgapiserver.domain.user.persistance.repository

import com.oneplace.dgapiserver.domain.user.persistance.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUid(uid: String): Boolean
    fun findByUid(uid: String): User?
}
