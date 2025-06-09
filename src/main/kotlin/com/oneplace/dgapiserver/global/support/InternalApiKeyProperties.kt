package com.oneplace.dgapiserver.global.support

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "api.internal")
class InternalApiKeyProperties {
    lateinit var apiKey: String
}