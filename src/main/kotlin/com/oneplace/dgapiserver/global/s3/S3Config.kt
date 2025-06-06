package com.oneplace.dgapiserver.global.s3

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config(
    private val s3Properties: S3Properties
) {
    @Bean
    fun amazonS3Client(): AmazonS3 = AmazonS3ClientBuilder.standard()
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(s3Properties.accessKey, s3Properties.secretKey)
            )
        )
        .withRegion(s3Properties.region)
        .build()
}