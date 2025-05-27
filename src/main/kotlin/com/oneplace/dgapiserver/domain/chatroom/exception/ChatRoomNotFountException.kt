package com.oneplace.dgapiserver.domain.chatroom.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.chatroom.ChatroomError

class ChatroomNotFountException: CustomException(ChatroomError.CHATROOM_NOT_FOUNT)
