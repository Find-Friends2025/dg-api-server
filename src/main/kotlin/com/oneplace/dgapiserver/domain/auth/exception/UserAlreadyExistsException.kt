package com.oneplace.dgapiserver.domain.auth.exception

import com.oneplace.dgapiserver.global.error.user.UserError
import com.oneplace.dgapiserver.global.error.user.UserException

class UserAlreadyExistsException : UserException(UserError.USER_ALREADY_EXISTS)
