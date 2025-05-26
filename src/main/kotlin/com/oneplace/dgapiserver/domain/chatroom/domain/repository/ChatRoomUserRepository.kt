package com.oneplace.dgapiserver.domain.chatroom.domain.repository

import com.oneplace.dgapiserver.domain.account.domain.Account
import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoom
import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoomUser
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ChatRoomUserRepository: JpaRepository<ChatRoomUser, Long> {
    @Query("""
    SELECT c
    FROM chat_room_user c
    JOIN FETCH c.chatRoom cr
    JOIN FETCH c.user u
    WHERE c.chatRoom IN (
        SELECT cru.chatRoom
        FROM chat_room_user cru
        WHERE cru.user = :user
    ) AND c.user != :user
""")
    fun findChatRoomUsersByUser(@Param("user") user: Account): List<ChatRoomUser>

    fun existsByChatRoomAndUser(chatRoom: ChatRoom, user: Account): Boolean

    @EntityGraph(attributePaths = ["user"])
    fun findByChatRoom(chatRoom: ChatRoom): List<ChatRoomUser>
}
