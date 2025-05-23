package com.oneplace.dgapiserver.global.error.chat

import org.springframework.http.HttpStatus

enum class ChatError(
    val message: String,
    val status: HttpStatus
) {
    BAD_REQUEST("Bad Request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_PERMISSION("Invalid Permission", HttpStatus.FORBIDDEN),
    INVALID_FIREBASE_TOKEN("Invalid Firebase token", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    CHATROOM_NOT_FOUND("Chat Room not found", HttpStatus.NOT_FOUND)
}
