package com.oneplace.dgapiserver.global.error.image

import com.oneplace.dgapiserver.global.error.CustomErrorCode
import org.springframework.http.HttpStatus

enum class ImageError(
    override val message: String,
    override val status: HttpStatus
): CustomErrorCode {
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_ERROR("An error occurred while uploading the file.", HttpStatus.BAD_REQUEST),
}