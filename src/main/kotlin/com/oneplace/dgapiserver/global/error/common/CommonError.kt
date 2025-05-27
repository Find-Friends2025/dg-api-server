package com.oneplace.dgapiserver.global.error.common

import com.oneplace.dgapiserver.global.error.CustomErrorCode
import org.springframework.http.HttpStatus

enum class CommonError(
    override val message: String,
    override val status: HttpStatus
): CustomErrorCode {
    INVALID_PERMISSION_EXCEPTION("Internal Server Error", HttpStatus.FORBIDDEN),
}