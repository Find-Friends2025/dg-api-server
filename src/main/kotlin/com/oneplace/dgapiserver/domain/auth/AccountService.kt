package com.oneplace.dgapiserver.domain.auth

interface AccountService {
    fun existsByUid(uid: String): Boolean
}
