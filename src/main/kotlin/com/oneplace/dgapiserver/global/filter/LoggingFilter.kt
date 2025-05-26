package com.oneplace.dgapiserver.global.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class LoggingFilter : OncePerRequestFilter() {

    private val log: Logger = LoggerFactory.getLogger(LoggingFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val requestWrapper = ContentCachingRequestWrapper(request)
        val responseWrapper = ContentCachingResponseWrapper(response)
        try {
            filterChain.doFilter(requestWrapper, responseWrapper)
        } catch (e: Exception) {
            log.error("LoggingFilter의 FilterChain에서 예외가 발생했습니다.", e)
        } finally {
            try {
                responseWrapper.copyBodyToResponse()
            } catch (e: IOException) {
                log.error("LoggingFilter에서 response body를 출력하는 도중 예외가 발생했습니다.", e)
            }
        }
    }
}
