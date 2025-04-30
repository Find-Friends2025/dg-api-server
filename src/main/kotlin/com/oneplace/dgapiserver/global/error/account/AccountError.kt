package com.oneplace.dgapiserver.global.error.account

import org.springframework.http.HttpStatus

enum class AccountError(
    val message: String,
    val status: HttpStatus
) {
    BAD_REQUEST("Bad Request", HttpStatus.BAD_REQUEST),
    INVALID_FIREBASE_TOKEN("Invalid Firebase token", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("This user has already completed registration", HttpStatus.CONFLICT)
}
