package com.oneplace.dgapiserver.global.error.chat

open class ChatException(
    val errorCode: ChatError
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}
