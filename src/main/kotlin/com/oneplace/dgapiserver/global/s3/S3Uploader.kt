package com.oneplace.dgapiserver.global.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.ListObjectsV2Request
import com.amazonaws.services.s3.model.PutObjectRequest
import com.oneplace.dgapiserver.global.support.logger
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@Component
class S3Uploader(
    private val amazonS3: AmazonS3,
    private val s3Properties: S3Properties,
) {

    private val log = logger()

    fun upload(multipartFile: MultipartFile, dirName: String): String {
        val uploadFile = convert(multipartFile)
        return upload(uploadFile, dirName)
    }

    private fun upload(uploadFile: File, dirName: String): String {
        val fileName = "$dirName/${uploadFile.name}"
        val uploadImageUrl = putS3(uploadFile, fileName)
        removeLocalFile(uploadFile)
        return uploadImageUrl
    }

    private fun putS3(uploadFile: File, fileName: String): String {
        amazonS3.putObject(PutObjectRequest(s3Properties.bucket, fileName, uploadFile))
        return amazonS3.getUrl(s3Properties.bucket, fileName).toString()
    }

    private fun removeLocalFile(targetFile: File) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다: ${targetFile.name}")
        } else {
            log.warn("파일 삭제 실패: ${targetFile.name}")
        }
    }

    private fun convert(multipartFile: MultipartFile): File {
        val file = File(multipartFile.originalFilename ?: throw IllegalArgumentException("파일명이 없습니다."))
        FileOutputStream(file).use { fos ->
            fos.write(multipartFile.bytes)
        }
        return file
    }
}
