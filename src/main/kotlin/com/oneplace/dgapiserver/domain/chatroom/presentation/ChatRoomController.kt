package com.oneplace.dgapiserver.domain.chatroom.presentation

import com.oneplace.dgapiserver.domain.chatroom.application.ChatRoomService
import com.oneplace.dgapiserver.domain.chatroom.application.dto.response.ChatRoomResponse
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/chatroom")
class ChatRoomController(
    private val chatRoomService: ChatRoomService
) {
    @PostMapping("/{targetUserId}")
    fun createChatRoom(@PathVariable targetUserId: Long){
        chatRoomService.saveCharRoom(targetUserId)
    }

    @GetMapping
    fun getChatRooms(): List<ChatRoomResponse> {
        return chatRoomService.getChatRooms()
    }

    @PostMapping("/{chatRoomId}/join-check")
    fun checkJoinChatRoom(
        @PathVariable chatRoomId: UUID
    ) {
        chatRoomService.checkJoinChatRoom(chatRoomId)
    }

    @PatchMapping("/{chatRoomId}")
    fun blockChatRoom(@PathVariable chatRoomId: UUID){
        chatRoomService.blockChatRoom(chatRoomId)
    }
}