package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import com.oneplace.dgapiserver.domain.account.exception.InvalidFirebaseTokenException
import com.oneplace.dgapiserver.domain.account.exception.UserNotFoundException
import com.oneplace.dgapiserver.global.firebase.FirebaseTokenVerifier
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
    private val firebaseTokenVerifier: FirebaseTokenVerifier,
    private val accountRepository: AccountRepository
) : AccountService {
    override fun existsByUid(uid: String): Boolean {
        return accountRepository.existsByUid(uid)
    }

    override fun handleFirebaseLogin(idToken: String): ResponseEntity<Any> {
        val decodedToken = try {
            firebaseTokenVerifier.verifyIdToken(idToken)
        } catch (e: Exception) {
            throw InvalidFirebaseTokenException()
        }

        if (!accountRepository.existsByUid(decodedToken.uid)) {
            throw UserNotFoundException(decodedToken.uid)
        }

        return ResponseEntity.ok(mapOf("message" to "Login Success"))
    }
}

