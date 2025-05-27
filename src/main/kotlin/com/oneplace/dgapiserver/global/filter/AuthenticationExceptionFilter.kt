package com.oneplace.dgapiserver.global.filter

import com.oneplace.dgapiserver.global.error.auth.AuthError
import com.oneplace.dgapiserver.global.security.ErrorResponseSender
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthenticationExceptionFilter(
    private val errorResponseSender: ErrorResponseSender,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            when (e) {
                is ExpiredJwtException -> errorResponseSender.send(response, AuthError.EXPIRED_TOKEN)
                is MalformedJwtException -> errorResponseSender.send(response, AuthError.MALFORMED_TOKEN)
                is UnsupportedJwtException -> errorResponseSender.send(response, AuthError.UNSUPPORTED_TOKEN)
                is IllegalArgumentException -> errorResponseSender.send(response, AuthError.ILLEGAL_ARGUMENT)
                else -> throw e
            }
            return
        }
    }
}
