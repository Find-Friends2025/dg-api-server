package com.oneplace.dgapiserver.domain.user.exception

import com.oneplace.dgapiserver.global.error.BusinessException
import com.oneplace.dgapiserver.global.error.ErrorCode

class InvalidFirebaseTokenException : BusinessException(ErrorCode.INVALID_FIREBASE_TOKEN)
