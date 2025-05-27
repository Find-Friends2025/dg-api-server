package com.oneplace.dgapiserver.global.error.auth

import com.oneplace.dgapiserver.global.error.CustomErrorCode
import org.springframework.http.HttpStatus

enum class AuthError(
    override val message: String,
    override val status: HttpStatus,
): CustomErrorCode {
    EXPIRED_TOKEN("Expired Token", HttpStatus.UNAUTHORIZED),
    MALFORMED_TOKEN("잘못된 토큰형식", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_TOKEN("지원하지 않는 토큰", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT("잘못된 인자", HttpStatus.BAD_REQUEST);
}
