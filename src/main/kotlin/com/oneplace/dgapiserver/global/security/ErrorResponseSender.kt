package com.oneplace.dgapiserver.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.oneplace.dgapiserver.global.error.CustomErrorCode
import com.oneplace.dgapiserver.global.error.ErrorResponse
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class ErrorResponseSender(
    private val objectMapper: ObjectMapper
) {

    fun send(response: HttpServletResponse, code: CustomErrorCode) {
        val body = ErrorResponse(
            message = code.message,
            status = code.status.value()
        )

        try {
            response.status = code.status.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"
            response.writer.write(objectMapper.writeValueAsString(body))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
