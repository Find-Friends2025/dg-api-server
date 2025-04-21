package com.oneplace.dgapiserver.domain.auth

import org.springframework.http.ResponseEntity

interface AccountService {
    fun existsByUid(uid: String): Boolean
    fun handleFirebaseLogin(idToken: String): ResponseEntity<Any>
}
