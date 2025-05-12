package com.oneplace.dgapiserver.global.s3

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "cloud.aws.s3")
class S3Properties {
    lateinit var bucket: String
}
