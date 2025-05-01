package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import com.oneplace.dgapiserver.domain.account.exception.InvalidFirebaseTokenException
import com.oneplace.dgapiserver.domain.account.exception.UserAlreadyExistsException
import com.oneplace.dgapiserver.domain.account.exception.UserNotFoundException
import com.oneplace.dgapiserver.domain.auth.api.dto.request.RegisterRequest
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
            throw InvalidFirebaseTokenException()
        }

        if (!accountRepository.existsByUid(decodedToken.uid)) {
            throw UserNotFoundException()
        }

        return ResponseEntity.ok(mapOf("message" to "Login Success"))
    }

    override fun registerUser(registerRequest: RegisterRequest): ResponseEntity<Any> {
        val account = accountRepository.findByUid(registerRequest.uid)
            ?: throw UserNotFoundException()

        if (account.isRegistered) {
            throw UserAlreadyExistsException()
        }

        account.gender = registerRequest.gender
        account.birth = registerRequest.birth
        account.location = registerRequest.location
        account.nickname = registerRequest.nickname
        account.profilePicUrl = registerRequest.profilePicUrl
        account.isRegistered = true

        accountRepository.save(account)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(mapOf("message" to "Registration Success"))
    }

}

