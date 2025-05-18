package com.oneplace.dgapiserver.domain.chatroom.domain

import jakarta.persistence.*
import java.util.*

@Entity(name = "chat_room")
class ChatRoom (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID = UUID.randomUUID(),

    var lastMessageId: String? = null,

    var lastMessage: String? = null,

    var lastMessageDate: Date? = null,

    var isBlocked: Boolean = false,
){
    fun block(){
        if (!this.isBlocked) this.isBlocked = true
    }
}