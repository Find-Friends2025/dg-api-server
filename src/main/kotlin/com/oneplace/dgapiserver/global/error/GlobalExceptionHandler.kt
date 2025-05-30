package com.oneplace.dgapiserver.global.error

import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun stageExceptionHandler(e: CustomException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse.of(e.errorCode), HttpStatus.valueOf(e.status))
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(e: BindException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of(e.message, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(MethodArgumentNotValidException::class, HttpMessageNotReadableException::class)
    fun handleValidationException(e: Exception): ResponseEntity<ErrorResponse> {
        val errorMessage = when (e) {
            is MethodArgumentNotValidException -> methodArgumentNotValidExceptionToJson(e.bindingResult)
            is HttpMessageNotReadableException -> "요청 본문을 읽을 수 없습니다."
            else -> "잘못된 요청입니다."
        }
        return ResponseEntity(ErrorResponse.of(errorMessage, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST)
    }

    private fun methodArgumentNotValidExceptionToJson(bindingResult: BindingResult): String {
        val fieldErrors = bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
        return fieldErrors.toString().replace("\"", "'")
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(e: DataIntegrityViolationException): ResponseEntity<DataErrorResponse> =
        ResponseEntity(ErrorResponse.of(e), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(e: NoHandlerFoundException): ResponseEntity<NoHandlerErrorResponse> =
        ResponseEntity(ErrorResponse.of(e), HttpStatus.FORBIDDEN)

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of("필드 유효성 검사에 실패했습니다: ${e.message}", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(RuntimeException::class)
    fun handleException(e: RuntimeException): ResponseEntity<ErrorResponse> =
        ResponseEntity(ErrorResponse.of(e.message, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR)
}
