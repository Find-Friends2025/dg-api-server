package com.oneplace.dgapiserver.domain.auth.application

import com.oneplace.dgapiserver.domain.user.persistance.User
import com.oneplace.dgapiserver.global.security.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserAuthenticationHolder {
    fun current(): User {
        return (SecurityContextHolder.getContext().authentication.principal as CustomUserDetails).user
    }
}
