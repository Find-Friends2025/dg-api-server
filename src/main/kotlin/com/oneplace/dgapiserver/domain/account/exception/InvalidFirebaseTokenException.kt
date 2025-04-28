package com.oneplace.dgapiserver.domain.account.exception

import com.oneplace.dgapiserver.global.error.BusinessException
import com.oneplace.dgapiserver.global.error.account.ErrorCode

class InvalidFirebaseTokenException : BusinessException(ErrorCode.INVALID_FIREBASE_TOKEN)
