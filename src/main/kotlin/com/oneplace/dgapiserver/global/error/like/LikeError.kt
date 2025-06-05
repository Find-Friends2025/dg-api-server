package com.oneplace.dgapiserver.global.error.like

import com.oneplace.dgapiserver.global.error.CustomErrorCode
import org.springframework.http.HttpStatus

enum class LikeError(
    override val message: String,
    override val status: HttpStatus
): CustomErrorCode {
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_ERROR("An error occurred while uploading the file.", HttpStatus.BAD_REQUEST),
    CANNOT_LIKE_SELF("You cannot like yourself.", HttpStatus.BAD_REQUEST),
    ALREADY_LIKED_USER("You have already liked this user.", HttpStatus.CONFLICT),
    LIKE_NOT_FOUND("No like record found to cancel.", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("User not found.", HttpStatus.NOT_FOUND)
}
