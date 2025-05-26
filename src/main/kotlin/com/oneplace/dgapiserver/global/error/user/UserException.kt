package com.oneplace.dgapiserver.global.error.user

open class UserException(
    val errorCode: UserError
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}
