package com.oneplace.dgapiserver.domain.chatroom.exception

import com.oneplace.dgapiserver.global.error.chat.ChatError
import com.oneplace.dgapiserver.global.error.chat.ChatException


class InvalidPermissionException: ChatException(ChatError.INVALID_PERMISSION)
