package com.oneplace.dgapiserver.domain.image.application

import com.oneplace.dgapiserver.domain.image.application.dto.response.UrlResponse
import com.oneplace.dgapiserver.domain.image.exception.FileUploadErrorException
import com.oneplace.dgapiserver.domain.user.application.UserService
import com.oneplace.dgapiserver.global.s3.S3Uploader
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class ImageService(
    private val s3Uploader: S3Uploader,
    private val userService: UserService
) {

    fun uploadImage(multipartFile: MultipartFile?): UrlResponse {
        if (multipartFile == null) {
            throw IllegalArgumentException("업로드할 파일이 없습니다.")
        }

        return try {
            val imageUrl = s3Uploader.upload(multipartFile, "profile")
            userService.updateProfileImage(imageUrl)
            UrlResponse(imageUrl)
        } catch (e: IOException) {
            throw FileUploadErrorException()
        }
    }
}

