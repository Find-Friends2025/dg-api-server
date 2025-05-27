package com.oneplace.dgapiserver.global.error

open class CustomException(
    val errorCode: CustomErrorCode
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}