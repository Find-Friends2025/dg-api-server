package com.oneplace.dgapiserver.global.error.account

open class AccountException(
    val errorCode: AccountError
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}
