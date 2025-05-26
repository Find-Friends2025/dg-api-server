package com.oneplace.dgapiserver.domain.chatroom.exception

import com.oneplace.dgapiserver.global.error.chatroom.ChatroomError
import com.oneplace.dgapiserver.global.error.chatroom.ChatroomException

class ChatroomNotFountException: ChatroomException(ChatroomError.CHATROOM_NOT_FOUNT)