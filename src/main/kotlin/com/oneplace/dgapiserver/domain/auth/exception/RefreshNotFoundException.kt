package com.oneplace.dgapiserver.domain.auth.exception

import com.oneplace.dgapiserver.global.error.user.UserException
import com.oneplace.dgapiserver.global.error.user.UserError

class RefreshNotFoundException : UserException(UserError.REFRESH_NOT_FOUND)

