package com.oneplace.dgapiserver.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.oneplace.dgapiserver.global.error.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.characterEncoding = "utf-8"
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(
            ErrorResponse("Access Denied", HttpStatus.FORBIDDEN.value())
        ))
    }
}
