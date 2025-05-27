package com.oneplace.dgapiserver.domain.auth.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.user.UserError

class InvalidFirebaseTokenException : CustomException(UserError.INVALID_FIREBASE_TOKEN)
