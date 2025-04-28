package com.oneplace.dgapiserver.global.error.image

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val message: String,
    val status: HttpStatus
) {
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_ERROR("An error occurred while uploading the file.", HttpStatus.BAD_REQUEST),
}
