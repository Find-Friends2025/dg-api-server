package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.auth.api.dto.request.RegisterRequest

interface AccountService {
    fun handleFirebaseLogin(idToken: String)
    fun registerUser(registerRequest: RegisterRequest)
}