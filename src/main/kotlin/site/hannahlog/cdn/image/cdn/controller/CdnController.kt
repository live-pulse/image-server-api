package site.hannahlog.cdn.image.cdn.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.hannahlog.cdn.image.cdn.dto.request.UploadRequest
import site.hannahlog.cdn.image.cdn.dto.response.UploadResponse
import site.hannahlog.cdn.image.cdn.service.CdnService

@RestController
@RequestMapping("/v1/api")
class CdnController(
    private val cdnService: CdnService
) {

    @CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
    @PostMapping(value = ["/upload"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload(request: UploadRequest): ResponseEntity<UploadResponse> {
        val result = cdnService.upload(request)
        return ResponseEntity.ok(result)
    }

}