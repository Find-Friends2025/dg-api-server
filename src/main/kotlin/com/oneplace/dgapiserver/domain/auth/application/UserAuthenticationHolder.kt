package com.oneplace.dgapiserver.domain.auth.application

import com.oneplace.dgapiserver.domain.user.persistance.User
import org.springframework.stereotype.Component

@Component
class UserAuthenticationHolder {
    fun current(): User {
        //TODO principal 에서 가져오는 로직으로 변경
        return User(
            uid= "",
            nickname = "",
            profilePicUrl= null
        )
    }
}
