package com.oneplace.dgapiserver.domain.auth.exception

import com.oneplace.dgapiserver.global.error.user.UserException
import com.oneplace.dgapiserver.global.error.user.UserError

class UserNotFoundException : UserException(UserError.USER_NOT_FOUND)
