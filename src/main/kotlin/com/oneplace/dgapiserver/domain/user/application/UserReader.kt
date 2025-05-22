package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.account.domain.Account
import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class UserReader(
    private val userRepository: AccountRepository
) {

    fun readByUidOrNull(uid: String): Account? =
        userRepository.findByUid(uid)

}
