package com.oneplace.dgapiserver.global.response

interface ResponseInterface {
    val status: Int
    val success: Boolean
    val state: String
    val message: String
}
