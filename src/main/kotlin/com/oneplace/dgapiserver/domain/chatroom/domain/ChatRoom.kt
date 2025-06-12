package com.oneplace.dgapiserver.domain.chatroom.domain

import jakarta.persistence.*
import java.util.*

@Entity(name = "chat_room")
class ChatRoom (
    @Id
    val id: UUID = UUID.randomUUID(),

    var lastMessageId: String? = null,

    var lastMessage: String? = null,

    var lastMessageDate: Date? = null,

    var isBlocked: Boolean = false,
){
    fun block(){
        if (!this.isBlocked) this.isBlocked = true
    }

    fun update(lastMessageId: String , lastMessage: String, lastMessageDate: Date){
        this.lastMessageId = lastMessageId
        this.lastMessage = lastMessage
        this.lastMessageDate = lastMessageDate
    }
}