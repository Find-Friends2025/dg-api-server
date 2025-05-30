package com.oneplace.dgapiserver.global.error.user

import com.oneplace.dgapiserver.global.error.CustomErrorCode
import org.springframework.http.HttpStatus

enum class UserError(
    override val message: String,
    override val status: HttpStatus
): CustomErrorCode {
    BAD_REQUEST("Invalid request", HttpStatus.BAD_REQUEST),
    INVALID_FIREBASE_TOKEN("Invalid Firebase token", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("Firebase authentication is successful, but there is no member information", HttpStatus.NOT_FOUND),
    REFRESH_NOT_FOUND("Refresh token is not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("You are already a registered member", HttpStatus.CONFLICT)
}
