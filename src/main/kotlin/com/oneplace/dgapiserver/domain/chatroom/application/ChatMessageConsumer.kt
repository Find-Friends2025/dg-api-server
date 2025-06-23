package com.oneplace.dgapiserver.domain.chatroom.application

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.data.redis.connection.stream.*
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.stream.StreamMessageListenerContainer
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class ChatMessageConsumer(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val chatRoomService: ChatRoomService,
) {

    @EventListener(ApplicationReadyEvent::class)
    fun startListening() {
        val options = StreamMessageListenerContainer
            .StreamMessageListenerContainerOptions
            .builder()
            .pollTimeout(Duration.ofSeconds(1))
            .build()

        val container = StreamMessageListenerContainer.create(redisTemplate.connectionFactory!!, options)

        container.receiveAutoAck(
            Consumer.from("api-consumer-group", "api-consumer-1"),
            StreamOffset.create("chat:stream", ReadOffset.lastConsumed())
        ) { message: MapRecord<String, String, String> ->
            handleMessage(message)
        }

        container.start()
    }

    private fun handleMessage(message: MapRecord<String, String, String>) {
        val value = message.value
        val roomId = UUID.fromString(value["roomId"]!!)
        val senderId = value["senderId"]!!
        val messageId = message.id.toString()
        val messageContent = value["message"] ?: ""
        val messageDateStr = value["sendAt"]!!
        val parsedDateTime = LocalDateTime.parse(messageDateStr)
        val date = Date.from(parsedDateTime.atZone(ZoneId.systemDefault()).toInstant())
        chatRoomService.updateChatRoomAndUnreadCount(
            roomId = roomId,
            senderId = senderId,
            messageId = messageId,
            messageContent = messageContent,
            messageDate = date
        )
    }
}