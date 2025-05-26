package com.oneplace.dgapiserver.global.error.common

import org.springframework.http.HttpStatus

enum class CommonError(
    val message: String,
    val status: HttpStatus
) {
    INVALID_PERMISSION_EXCEPTION("Internal Server Error", HttpStatus.FORBIDDEN),
}