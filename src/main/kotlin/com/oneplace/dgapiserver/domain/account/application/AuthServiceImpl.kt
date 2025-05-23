package com.oneplace.dgapiserver.domain.account.application

import com.oneplace.dgapiserver.domain.account.application.dto.AuthLoginDto
import com.oneplace.dgapiserver.domain.account.application.dto.AuthTokenDto
import com.oneplace.dgapiserver.domain.account.application.dto.RegisterReqDto
import com.oneplace.dgapiserver.domain.account.domain.Account
import com.oneplace.dgapiserver.domain.account.domain.repository.AccountRepository
import com.oneplace.dgapiserver.domain.account.exception.InvalidFirebaseTokenException
import com.oneplace.dgapiserver.domain.account.exception.UserAlreadyExistsException
import com.oneplace.dgapiserver.domain.account.exception.UserNotFoundException
import com.oneplace.dgapiserver.domain.user.application.UserProcessor
import com.oneplace.dgapiserver.domain.user.application.UserReader
import com.oneplace.dgapiserver.global.firebase.FirebaseTokenVerifier
import com.oneplace.dgapiserver.global.jwt.JwtGenerator
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val firebaseTokenVerifier: FirebaseTokenVerifier,
    private val accountRepository: AccountRepository,
    private val authProcessor: AuthProcessor,
    private val userMapper: AuthMapper,
    private val userProcessor: UserProcessor,
    private val jwtGenerator: JwtGenerator,
    private val authMapper: AuthMapper,
    private val authReader: AuthReader,
    private val userReader: UserReader,
) : AuthService {

    override fun login(idToken: String): AuthLoginDto {
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

        val user = userProcessor.getUserOrCreate(uid)
        val tokenDto = generateToken(user)
        return authMapper.login(tokenDto, user)
    }

    override fun refresh(token: String): AuthTokenDto {
        val removePrefixToken = token.removePrefix("Bearer ").trim()
        val refreshToken = authReader.read(removePrefixToken)
        val user = userReader.read(refreshToken.userId)
        return generateToken(user)
    }

    override fun register(request: RegisterReqDto): AuthTokenDto {
        if (accountRepository.existsByUid(request.uid)) {
            throw UserAlreadyExistsException()
        }

        val user = Account.of(request.uid)
        val signedUser = userProcessor.signUp(user, request)
        return generateToken(signedUser)
    }

    private fun generateToken(user: Account): AuthTokenDto {
        val token = jwtGenerator.generateToken(
            userId = user.id.toString(),
            authority = user.authority,
        )
        authProcessor.saveRefreshToken(
            userId = user.id,
            token = token.refreshToken
        )
        return userMapper.map(token)
    }

}

