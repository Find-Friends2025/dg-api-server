package com.oneplace.dgapiserver.domain.common.exception

import com.oneplace.dgapiserver.global.error.common.CommonError
import com.oneplace.dgapiserver.global.error.common.CommonException

class InvalidPermissionException: CommonException(CommonError.INVALID_PERMISSION_EXCEPTION)
