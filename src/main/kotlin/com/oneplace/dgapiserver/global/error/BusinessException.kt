package com.oneplace.dgapiserver.global.error

open class BusinessException(
    val errorCode: ErrorCode
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}
