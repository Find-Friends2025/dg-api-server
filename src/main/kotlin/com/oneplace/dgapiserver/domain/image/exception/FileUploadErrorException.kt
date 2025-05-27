package com.oneplace.dgapiserver.domain.image.exception

import com.oneplace.dgapiserver.global.error.CustomException
import com.oneplace.dgapiserver.global.error.image.ImageError


class FileUploadErrorException : CustomException(ImageError.FILE_ERROR)
