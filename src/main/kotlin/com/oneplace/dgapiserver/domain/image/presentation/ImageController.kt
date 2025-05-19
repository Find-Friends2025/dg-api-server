package com.oneplace.dgapiserver.domain.image.presentation

import com.oneplace.dgapiserver.domain.image.application.ImageService
import com.oneplace.dgapiserver.domain.image.application.dto.response.UrlResponse
import com.oneplace.dgapiserver.global.response.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController(
    private val imageService: ImageService
) {

    @PostMapping("/upload")
    fun uploadImage(
        @RequestPart("image") multipartFile: MultipartFile?
    ): BaseResponse<UrlResponse> {
        return BaseResponse(
            message = "Image uploaded successfully",
            data = imageService.uploadImage(multipartFile)
        )
    }
}

