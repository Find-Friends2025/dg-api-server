package com.oneplace.dgapiserver.domain.chatroom.application.dto.response

import java.util.*

data class ChatRoomResponse(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val lastMessageId: String? = null,
    val lastMessage: String? = null,
    val lastMessageDate: Date? = null,
    val profileImageUrl: String? = null,
    val unreadMessagesCount: Int = 0,
)
