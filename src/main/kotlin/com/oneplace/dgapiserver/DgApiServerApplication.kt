package com.oneplace.dgapiserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class DgApiServerApplication

fun main(args: Array<String>) {
    runApplication<DgApiServerApplication>(*args)
}
