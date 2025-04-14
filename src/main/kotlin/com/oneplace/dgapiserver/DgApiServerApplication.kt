package com.oneplace.dgapiserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DgApiServerApplication

fun main(args: Array<String>) {
    runApplication<DgApiServerApplication>(*args)
}
