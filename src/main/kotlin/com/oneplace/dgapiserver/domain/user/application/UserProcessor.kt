package com.oneplace.dgapiserver.domain.user.application

import com.oneplace.dgapiserver.domain.account.application.dto.RegisterReqDto
import com.oneplace.dgapiserver.domain.account.domain.Account
import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class UserProcessor(
    private val userReader: UserReader,
    private val userRepository: AccountRepository
) {

    fun getUserOrCreate(uid: String): Account =
        userReader.readByUidOrNull(uid)
            ?: userRepository.save(Account.of(uid))

    fun signUp(user: Account, dto: RegisterReqDto): Account {
        user.signUp(dto)
        return userRepository.save(user)
    }

}
