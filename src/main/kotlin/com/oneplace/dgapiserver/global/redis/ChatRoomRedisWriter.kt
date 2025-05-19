package com.oneplace.dgapiserver.global.redis

import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoom
import com.oneplace.dgapiserver.domain.account.domain.Account
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class ChatRoomRedisWriter(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val ttl: Duration = Duration.ofHours(3)

    fun saveChatRoom(chatRoom: ChatRoom, users: List<Account>) {
        val chatRoomKey = "chat:info:${chatRoom.id}"
        val chatRoomUserKey = "$chatRoomKey:users"

        val roomMap = mapOf(
            "roomName" to (chatRoom.lastMessage ?: "채팅방"),
            "createdAt" to System.currentTimeMillis().toString(),
            "ownerId" to (users.firstOrNull()?.id?.toString() ?: "")
        )
        redisTemplate.opsForHash<String, String>().putAll(chatRoomKey, roomMap)
        redisTemplate.expire(chatRoomKey, ttl)

        val userIds = users.map { it.id.toString() }
        redisTemplate.opsForSet().add(chatRoomUserKey, *userIds.toTypedArray())
        redisTemplate.expire(chatRoomUserKey, ttl)

        users.forEach { user ->
            val userKey = "chat:user:${user.id}"
            val userMap = mapOf(
                "name" to user.nickname,
                "profileImg" to (user.profilePicUrl ?: "")
            )
            redisTemplate.opsForHash<String, String>().putAll(userKey, userMap)
            redisTemplate.expire(userKey, ttl)
        }
    }
}