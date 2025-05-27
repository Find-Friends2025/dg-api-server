package com.oneplace.dgapiserver.global.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.oneplace.dgapiserver.global.error.CustomErrorCode
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class BaseResponse<T>(

    override val status: Int = HttpStatus.OK.value(),
    override val message: String,
    val data: T? = null,

    ) : ResponseInterface {

    // errorResponse constructor
    constructor(code: CustomErrorCode) : this(
        status = code.status.value(),
        message = code.message
    )
}