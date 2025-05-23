package com.oneplace.dgapiserver.domain.chatroom.exception

import com.oneplace.dgapiserver.global.error.BusinessException
import com.oneplace.dgapiserver.global.error.ErrorCode

class ChatRoomNotFountException: BusinessException(ErrorCode.CHATROOM_NOT_FOUND)