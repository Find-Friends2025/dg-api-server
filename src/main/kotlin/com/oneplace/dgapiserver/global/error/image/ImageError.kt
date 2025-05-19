
package com.oneplace.dgapiserver.global.error.image

open class ImageException(
    val errorCode: ImageError
) : RuntimeException(errorCode.message) {
    val status: Int get() = errorCode.status.value()
    override val message: String get() = errorCode.message
}

package com.oneplace.dgapiserver.global.error.image

import org.springframework.http.HttpStatus

enum class ImageError(
    val message: String,
    val status: HttpStatus
) {
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_ERROR("An error occurred while uploading the file.", HttpStatus.BAD_REQUEST),
}