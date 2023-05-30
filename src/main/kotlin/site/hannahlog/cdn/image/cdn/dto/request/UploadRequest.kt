package site.hannahlog.cdn.image.cdn.dto.request

import org.springframework.web.multipart.MultipartFile

data class UploadRequest(
    val path: UploadPath,
    val file: MultipartFile
)
