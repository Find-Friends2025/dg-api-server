package com.oneplace.dgapiserver.global.error.account

import org.springframework.http.HttpStatus

enum class AccountError(
    val message: String,
    val status: HttpStatus
) {
    BAD_REQUEST("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    INVALID_FIREBASE_TOKEN("유효하지 않은 Firebase 토큰입니다.", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("Firebase 인증은 되었지만, 회원 정보가 없습니다.", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("이미 회원가입이 완료된 회원입니다.", HttpStatus.CONFLICT)
}
