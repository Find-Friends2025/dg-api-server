package com.oneplace.dgapiserver.domain.like.application.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.like.LikeError

class LikeNotFoundException : CustomException(LikeError.LIKE_NOT_FOUND)
