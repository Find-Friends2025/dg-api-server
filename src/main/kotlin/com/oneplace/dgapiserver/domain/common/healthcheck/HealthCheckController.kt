package com.oneplace.dgapiserver.domain.common.healthcheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class HealthCheckController {
    @GetMapping("health-check")
    fun healthCheck(): String {
        return "OK"
    }
}