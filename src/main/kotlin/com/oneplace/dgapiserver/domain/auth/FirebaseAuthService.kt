package com.oneplace.dgapiserver.domain.auth

import com.google.firebase.auth.FirebaseAuth
import com.oneplace.dgapiserver.domain.account.AccountRepository
import com.oneplace.dgapiserver.domain.account.AuthResponse
import com.oneplace.dgapiserver.global.config.JwtProvider
import org.springframework.stereotype.Service

@Service
class FirebaseAuthService(
    private val accountRepository: AccountRepository,
    private val jwtProvider: JwtProvider
) {

    fun verifyIdToken(idToken: String): AuthResponse {
        val decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken)
        val phoneNumber = decodedToken.claims["phone_number"] as? String
            ?: throw IllegalArgumentException("토큰에 전화번호 정보가 없습니다.")

        val account = accountRepository.findByPhoneNumber(phoneNumber)

        return if (account != null) {
            val token = jwtProvider.generateToken(account.id, account.phoneNumber)
            AuthResponse(
                isNewUser = false,
                token = token,
                nickname = account.nickname
            )
        } else {
            AuthResponse(
                isNewUser = true,
                token = null // 또는 가입용 임시 토큰 생성해도 됨
            )
        }
    }
}

