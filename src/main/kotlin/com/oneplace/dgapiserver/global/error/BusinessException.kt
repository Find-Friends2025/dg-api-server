package com.oneplace.dgapiserver.global.error

import com.oneplace.dgapiserver.global.error.account.ErrorCode

open class BusinessException(
    val errorCode: ErrorCode
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}
