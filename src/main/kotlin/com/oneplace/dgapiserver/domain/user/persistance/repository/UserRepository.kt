package com.oneplace.dgapiserver.domain.user.persistance.repository

import com.oneplace.dgapiserver.domain.user.persistance.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    fun existsByUid(uid: String): Boolean
    fun findByUid(uid: String): User?
}
