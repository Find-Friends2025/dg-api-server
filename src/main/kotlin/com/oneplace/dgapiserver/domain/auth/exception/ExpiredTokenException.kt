package com.oneplace.dgapiserver.domain.auth.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.auth.AuthError

class ExpiredTokenException: CustomException(AuthError.EXPIRED_TOKEN)