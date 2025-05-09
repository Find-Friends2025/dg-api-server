package com.oneplace.dgapiserver.global.error

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.servlet.NoHandlerFoundException

data class ErrorResponse(
    val message: String,
    val status: Int
)  {
    companion object {
        fun of(e: BusinessException) =
            ErrorResponse(
                message = e.message,
                status = e.status
            )

        fun of(message: String?, status: HttpStatus) =
            ErrorResponse(
                message = message ?: "Unknown Exception",
                status = status.value()
            )

        fun of(e: DataIntegrityViolationException) = DataErrorResponse(
            message = e.message.toString(),
            status = ErrorCode.BAD_REQUEST.status.value()
        )

        fun of(e: NoHandlerFoundException) = NoHandlerErrorResponse(
            message = e.message.toString(),
            status = ErrorCode.BAD_REQUEST.status.value()
        )
    }
}

data class ValidationErrorResponse(
    val fieldError: Map<String, String?>,
    val status: Int
)

data class DataErrorResponse(
    val message: String,
    val status: Int
)

data class NoHandlerErrorResponse(
    val message: String,
    val status: Int
)
