package com.oneplace.dgapiserver.domain.like.application.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.like.LikeError

class AlreadyLikedUserException : CustomException(LikeError.ALREADY_LIKED_USER)
