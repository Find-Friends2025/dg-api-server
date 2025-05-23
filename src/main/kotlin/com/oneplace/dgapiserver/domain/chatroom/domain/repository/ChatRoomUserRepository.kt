package com.oneplace.dgapiserver.domain.chatroom.domain.repository

import com.oneplace.dgapiserver.domain.account.domain.Account
import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoom
import com.oneplace.dgapiserver.domain.chatroom.domain.ChatRoomUser
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ChatRoomUserRepository: JpaRepository<ChatRoomUser, Long> {
    fun findByUser(user: Account): List<ChatRoomUser>?
    @Query("""
    SELECT c
    FROM chat_room_user c
    JOIN FETCH c.chatRoom cr
    JOIN FETCH c.user u
    WHERE c.chatRoom IN (
        SELECT cr
        FROM chat_room_user cr
        WHERE cr.user = :user
    ) AND c.user != :user
""")
    fun findChatRoomUsersByUser(@Param("user") user: Account): List<ChatRoomUser>

    fun existByChatRoomAndUser(chatRoom: ChatRoom, user: Account): Boolean

    @EntityGraph(attributePaths = ["user"])
    fun findByChatRoom(chatRoom: ChatRoom): List<ChatRoomUser>
}
