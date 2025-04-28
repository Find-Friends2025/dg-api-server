package com.oneplace.dgapiserver.global.error.image

open class ImageException(
    val errorCode: ImageError
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}
