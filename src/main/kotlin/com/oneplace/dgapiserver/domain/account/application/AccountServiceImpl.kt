package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.domain.Account
import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import com.oneplace.dgapiserver.domain.account.exception.InvalidFirebaseTokenException
import com.oneplace.dgapiserver.domain.account.exception.UserAlreadyExistsException
import com.oneplace.dgapiserver.domain.account.exception.UserNotFoundException
import com.oneplace.dgapiserver.domain.auth.api.dto.request.RegisterRequest
import com.oneplace.dgapiserver.global.firebase.FirebaseTokenVerifier
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val firebaseTokenVerifier: FirebaseTokenVerifier,
    private val accountRepository: AccountRepository
) : AccountService {

    override fun handleFirebaseLogin(idToken: String) {
        val idToken = idToken.removePrefix("Bearer ").trim()

        val uid = try {
            val firebaseToken = firebaseTokenVerifier.verifyIdToken(idToken)
            firebaseToken.uid
        } catch (e: Exception) {
            throw InvalidFirebaseTokenException() // 401
        }

        if (!accountRepository.existsByUid(uid)) {
            throw UserNotFoundException() // 404
        }
    }

    override fun registerUser(registerRequest: RegisterRequest) {
        if (accountRepository.existsByUid(registerRequest.uid)) {
            throw UserAlreadyExistsException()
        }

        val account = Account(
            uid = registerRequest.uid,
            gender = registerRequest.gender,
            birth = registerRequest.birth,
            location = registerRequest.location,
            nickname = registerRequest.nickname,
            profilePicUrl = registerRequest.profilePicUrl,
            isRegistered = true
        )

        accountRepository.save(account)
    }

}