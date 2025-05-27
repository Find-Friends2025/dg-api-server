package com.oneplace.dgapiserver.domain.auth.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.user.UserError

class UserNotFoundException : CustomException(UserError.USER_NOT_FOUND)
