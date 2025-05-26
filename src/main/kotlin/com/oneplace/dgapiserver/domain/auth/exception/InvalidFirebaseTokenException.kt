package com.oneplace.dgapiserver.domain.auth.exception

import com.oneplace.dgapiserver.global.error.user.UserException
import com.oneplace.dgapiserver.global.error.user.UserError

class InvalidFirebaseTokenException : UserException(UserError.INVALID_FIREBASE_TOKEN)
