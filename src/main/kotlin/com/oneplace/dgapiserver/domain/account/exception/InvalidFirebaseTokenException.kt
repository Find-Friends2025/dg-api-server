package com.oneplace.dgapiserver.domain.account.exception

import com.oneplace.dgapiserver.global.error.account.AccountException
import com.oneplace.dgapiserver.global.error.account.AccountError

class InvalidFirebaseTokenException : AccountException(AccountError.INVALID_FIREBASE_TOKEN)
