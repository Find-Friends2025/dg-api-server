package com.oneplace.dgapiserver.domain.user.exception

import com.oneplace.dgapiserver.global.error.BusinessException
import com.oneplace.dgapiserver.global.error.ErrorCode

class UserNotFoundException : BusinessException(ErrorCode.USER_NOT_FOUND)

