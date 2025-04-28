package com.oneplace.dgapiserver.global.error.account

import org.springframework.http.HttpStatus

enum class AccountError(
    val message: String,
    val status: HttpStatus
) {
    BAD_REQUEST("Bad Request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),

    INVALID_FIREBASE_TOKEN("Invalid Firebase token", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND)
}
