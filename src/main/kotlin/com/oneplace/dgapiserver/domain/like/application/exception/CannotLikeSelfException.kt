package com.oneplace.dgapiserver.domain.like.application.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.like.LikeError

class CannotLikeSelfException : CustomException(LikeError.CANNOT_LIKE_SELF)

