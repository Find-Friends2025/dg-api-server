package com.oneplace.dgapiserver.domain.chatroom.domain

import com.oneplace.dgapiserver.domain.account.domain.Account
import jakarta.persistence.*

@Entity(name = "chat_room_user")
class ChatRoomUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    val chatRoom: ChatRoom,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: Account,

    var unreadMessageCount: Int = 0,
)
