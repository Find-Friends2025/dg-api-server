package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.domain.Account
import org.springframework.stereotype.Component

@Component
class UserAuthenticationHolder {
    fun current(): Account {
        //TODO principal 에서 가져오는 로직으로 변경
        return Account(
            uid= "",
            nickname = "",
            profilePicUrl= null
        )
    }
}