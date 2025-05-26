package com.oneplace.dgapiserver.global.error.chatroom

import org.springframework.http.HttpStatus

enum class ChatroomError(
    val message: String,
    val status: HttpStatus
) {
    CHATROOM_NOT_FOUNT("chatRoom not fount", HttpStatus.NOT_FOUND),
}