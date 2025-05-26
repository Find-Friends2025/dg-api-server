package com.oneplace.dgapiserver.global.error.common

open class CommonException(
    val errorCode: CommonError
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}
