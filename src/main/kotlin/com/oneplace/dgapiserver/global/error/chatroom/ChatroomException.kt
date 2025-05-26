package com.oneplace.dgapiserver.global.error.chatroom

open class ChatroomException(
    val errorCode: ChatroomError
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}