package com.oneplace.dgapiserver.global.s3

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "cloud.aws")
class S3Properties {
    lateinit var accessKey: String
    lateinit var secretKey: String
    lateinit var region: String
    lateinit var bucket: String
}