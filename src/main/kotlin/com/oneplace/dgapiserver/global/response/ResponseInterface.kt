package com.oneplace.dgapiserver.global.response

interface ResponseInterface {
    val status: Int
    val state: String
    val message: String
}