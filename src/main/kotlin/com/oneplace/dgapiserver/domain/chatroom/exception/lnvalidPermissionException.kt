package com.oneplace.dgapiserver.domain.chatroom.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.chat.ChatError


class InvalidPermissionException: CustomException(ChatError.INVALID_PERMISSION)
