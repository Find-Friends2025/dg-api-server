package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.auth.api.dto.request.RegisterRequest
import org.springframework.http.ResponseEntity

interface AccountService {
    fun existsByUid(uid: String): Boolean
    fun handleFirebaseLogin(idToken: String): ResponseEntity<Any>
    fun registerUser(registerRequest: RegisterRequest): ResponseEntity<Any>
}
