package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import com.oneplace.dgapiserver.global.firebase.FirebaseTokenVerifier
import org.springframework.http.HttpStatus
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Firebase token")
        }

        val uid = decodedToken.uid

        return if (accountRepository.existsByUid(uid)) {
            ResponseEntity.ok(mapOf("message" to "Login Success"))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("message" to "User Not Found", "uid" to uid))
        }
    }
}

