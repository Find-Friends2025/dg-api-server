package com.oneplace.dgapiserver.domain.chatroom.domain.repository

import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ChatRoomRepository: JpaRepository<ChatRoom, UUID> {
}