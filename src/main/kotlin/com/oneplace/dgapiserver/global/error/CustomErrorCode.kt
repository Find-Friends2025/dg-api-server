package com.oneplace.dgapiserver.global.error

import org.springframework.http.HttpStatus

interface CustomErrorCode {

    val status: HttpStatus
    val message: String
}