package com.oneplace.dgapiserver.domain.account.exception

import com.oneplace.dgapiserver.global.error.account.AccountException
import com.oneplace.dgapiserver.global.error.account.AccountError

class UserNotFoundException : AccountException(AccountError.USER_NOT_FOUND)

