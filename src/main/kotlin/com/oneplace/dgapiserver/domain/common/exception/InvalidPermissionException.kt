package com.oneplace.dgapiserver.domain.common.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.common.CommonError

class InvalidPermissionException: CustomException(CommonError.INVALID_PERMISSION_EXCEPTION)
