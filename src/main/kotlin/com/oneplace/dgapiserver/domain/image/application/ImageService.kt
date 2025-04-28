package com.oneplace.dgapiserver.domain.image.application

import com.oneplace.dgapiserver.domain.image.application.dto.response.UrlResponse
import com.oneplace.dgapiserver.domain.image.exception.FileUploadErrorException
import com.oneplace.dgapiserver.global.s3.S3Uploader
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class ImageService(
    private val s3Uploader: S3Uploader
) {

    fun uploadImage(multipartFile: MultipartFile?): UrlResponse {
        try {
            return UrlResponse(s3Uploader.upload(multipartFile, "image"))
        } catch (e: IOException) {
            throw FileUploadErrorException()
        }
    }

}

