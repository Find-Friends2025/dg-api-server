package com.oneplace.dgapiserver.domain.account.exception

import com.oneplace.dgapiserver.global.error.account.AccountError
import com.oneplace.dgapiserver.global.error.account.AccountException

class UserAlreadyExistsException : AccountException(AccountError.USER_ALREADY_EXISTS)