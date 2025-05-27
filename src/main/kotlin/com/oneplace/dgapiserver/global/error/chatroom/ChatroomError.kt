package com.oneplace.dgapiserver.global.error.chatroom

import com.oneplace.dgapiserver.global.error.CustomErrorCode
import org.springframework.http.HttpStatus

enum class ChatroomError(
    override val message: String,
    override val status: HttpStatus
): CustomErrorCode {
    CHATROOM_NOT_FOUNT("chatRoom not fount", HttpStatus.NOT_FOUND),
}