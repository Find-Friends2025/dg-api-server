package com.oneplace.dgapiserver.domain.chatroom.application

import com.oneplace.dgapiserver.domain.chatroom.application.dto.response.ChatRoomResponse
import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoom
import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoomUser
import com.oneplace.dgapiserver.domain.chatroom.domain.repository.ChatRoomRepository
import com.oneplace.dgapiserver.domain.chatroom.domain.repository.ChatRoomUserRepository
import com.oneplace.dgapiserver.domain.chatroom.exception.ChatRoomNotFountException
import com.oneplace.dgapiserver.domain.user.application.UserAuthenticationHolder
import com.oneplace.dgapiserver.domain.user.domain.repository.AccountRepository
import com.oneplace.dgapiserver.global.error.InvalidPermissionException
import com.oneplace.dgapiserver.global.redis.ChatRoomRedisWriter
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository,
    private val accountRepository: AccountRepository,
    private val chatRoomUserRepository: ChatRoomUserRepository,
    private val authenticationHolder: UserAuthenticationHolder,
    private val chatRoomRedisWriter: ChatRoomRedisWriter
) {
    @Transactional(rollbackFor = [Exception::class])
    fun saveCharRoom(userId: Long){
        val targetUser = accountRepository.findByIdOrThrow(userId)
        val chatRoom = chatRoomRepository.save(ChatRoom())
        chatRoomUserRepository.saveAll(listOf(
            ChatRoomUser(chatRoom = chatRoom, user = authenticationHolder.current()),
            ChatRoomUser(chatRoom = chatRoom, user = targetUser)
        ))
    }

    @Transactional(rollbackFor = [Exception::class])
    fun blockChatRoom(chatRoomId: UUID){
        val chatRoom: ChatRoom =  chatRoomRepository.findByIdOrNull(chatRoomId)
            ?: throw ChatRoomNotFountException()
        val exists= chatRoomUserRepository.existByChatRoomAndUser(
            chatRoom= chatRoom,
            user= authenticationHolder.current()
        )
        if (!exists) throw InvalidPermissionException()
        chatRoom.block()
        chatRoomRepository.save(chatRoom)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun checkJoinChatRoom(chatRoomId: UUID) {
        val chatRoom: ChatRoom =  chatRoomRepository.findByIdOrNull(chatRoomId)
            ?: throw ChatRoomNotFountException()
        val chatRoomUsers: List<ChatRoomUser> = chatRoomUserRepository.findByChatRoom(chatRoom)
        chatRoomRedisWriter.saveChatRoom(
            chatRoom = chatRoom,
            users = chatRoomUsers.map { it.user }
        )
    }

    @Transactional(readOnly = true)
    fun getChatRooms(): List<ChatRoomResponse>{
        val chatRoomUsers = chatRoomUserRepository.findChatRoomUsersByUser(
            user= authenticationHolder.current()
        )
        return chatRoomUsers.map{ chatRoomUser ->
            val chatRoom = chatRoomUser.chatRoom
            val otherUser = chatRoomUser.user
            ChatRoomResponse(
                id= chatRoom.id,
                name= otherUser.nickname,
                lastMessageId= chatRoom.lastMessageId,
                lastMessage= chatRoom.lastMessage,
                lastMessageDate = chatRoom.lastMessageDate,
                profileImageUrl= otherUser.profilePicUrl,
                unreadMessagesCount= chatRoomUser.unreadMessageCount
            )
        }
    }
}