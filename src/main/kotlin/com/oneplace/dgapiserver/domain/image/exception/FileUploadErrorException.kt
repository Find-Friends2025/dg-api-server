package com.oneplace.dgapiserver.domain.image.exception

import com.oneplace.dgapiserver.global.error.image.ImageError
import com.oneplace.dgapiserver.global.error.image.ImageException


class FileUploadErrorException : ImageException(ImageError.FILE_ERROR)
