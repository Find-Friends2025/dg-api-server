package com.oneplace.dgapiserver.global.redis

import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoom
import com.oneplace.dgapiserver.domain.user.persistance.User
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class ChatRoomRedisWriter(
    private val redisTemplate: RedisTemplate<String, String>
) {
    private val ttl: Duration = Duration.ofHours(3)

    fun saveChatRoom(chatRoom: ChatRoom, users: List<User>) {
        val chatRoomKey = "chat:info:${chatRoom.id}"
        val chatRoomUserKey = "$chatRoomKey:users"

        // 방 정보 저장
        val roomMap = mapOf(
            "roomName" to (chatRoom.lastMessage ?: "채팅방"),
            "createdAt" to System.currentTimeMillis().toString(),
            "ownerId" to (users.firstOrNull()?.id?.toString() ?: "")
        )
        redisTemplate.opsForHash<String, String>().putAll(chatRoomKey, roomMap)
        redisTemplate.expire(chatRoomKey, ttl)

        // 기존 유저 목록 제거 후 새로 저장 (갱신 목적)
        redisTemplate.delete(chatRoomUserKey)

        // 유저 ID 목록 Set에 저장
        val userIds = users.map { it.id.toString() }
        println(userIds.joinToString(","))
        redisTemplate.opsForSet().add(chatRoomUserKey, *userIds.toTypedArray())
        redisTemplate.expire(chatRoomUserKey, ttl)

        // 각 유저 정보 저장
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
