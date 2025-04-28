package com.oneplace.dgapiserver.domain.account.exception

import com.oneplace.dgapiserver.global.error.BusinessException
import com.oneplace.dgapiserver.global.error.account.ErrorCode

class UserNotFoundException(val uid: String) : BusinessException(ErrorCode.USER_NOT_FOUND)

