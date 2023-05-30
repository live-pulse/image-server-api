package site.hannahlog.cdn.image.cdn.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import site.hannahlog.cdn.image.cdn.dto.request.UploadPath
import site.hannahlog.cdn.image.cdn.dto.request.UploadRequest
import site.hannahlog.cdn.image.cdn.dto.response.UploadResponse
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*

@Service
class CdnService {

    @Value("\${image.default.path}")
    private lateinit var defaultPath: String

    @Value("\${image.server.domain}")
    private lateinit var serverDomain: String

    fun upload(request: UploadRequest): UploadResponse {
        val data: String? = try {
            val requestPath: String = UploadPath.of(request.path)
            val fileName = "${Date()}_${request.file.originalFilename}"
            val path: Path = Paths.get(defaultPath.plus(requestPath), fileName)
            Files.write(path, request.file.bytes, StandardOpenOption.CREATE)
            "$serverDomain/${request.path}/$fileName"
        } catch (e: Exception) {
            println(e)
            null
        }
        return UploadResponse.of(data)
    }

}