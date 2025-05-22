package com.oneplace.dgapiserver.domain.account.exception

import com.oneplace.dgapiserver.global.error.account.AccountException
import com.oneplace.dgapiserver.global.error.account.AccountError

class RefreshNotFoundException : AccountException(AccountError.REFRESH_NOT_FOUND)

