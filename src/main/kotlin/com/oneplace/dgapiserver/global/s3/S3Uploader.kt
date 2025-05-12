package com.oneplace.dgapiserver.global.s3

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.ListObjectsV2Request
import com.amazonaws.services.s3.model.PutObjectRequest
import mu.KotlinLogging
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

    companion object {
        private val log = KotlinLogging.logger {}
    }

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
            log.info { "파일이 삭제되었습니다: ${targetFile.name}" }
        } else {
            log.warn { "파일 삭제 실패: ${targetFile.name}" }
        }
    }

    fun removeFaceAndroidFile(s3ImageUrl: String) {
        val key = extractKeyFromUrl(s3ImageUrl)
        if (key.startsWith("face-android/")) {
            amazonS3.deleteObject(s3Properties.bucket, key)
            log.info { "파일이 삭제되었습니다: $s3ImageUrl" }
        } else {
            log.warn { "버킷에 없는 파일입니다: $s3ImageUrl" }
        }
    }

    private fun extractKeyFromUrl(s3ImageUrl: String): String {
        val decodedUrl = URLDecoder.decode(s3ImageUrl, StandardCharsets.UTF_8)
        return decodedUrl.substringAfter(".com/")
    }

    fun getAllImageUrlsFromS3(directory: String): List<String> {
        val request = ListObjectsV2Request()
            .withBucketName(s3Properties.bucket)
            .withPrefix(directory)

        val result = amazonS3.listObjectsV2(request)

        return result.objectSummaries.map { s3Object ->
            amazonS3.getUrl(s3Properties.bucket, s3Object.key).toString()
        }
    }

    fun getObjectBytes(s3ImageUrl: String): ByteArray {
        val key = extractKeyFromUrl(s3ImageUrl)
        return amazonS3.getObject(GetObjectRequest(s3Properties.bucket, key)).objectContent.use { inputStream ->
            inputStream.readBytes()
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
