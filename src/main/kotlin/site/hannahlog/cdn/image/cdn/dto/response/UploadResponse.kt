package site.hannahlog.cdn.image.cdn.dto.response

data class UploadResponse(
    val data: String?
) {
    companion object {
        fun of(data: String?): UploadResponse {
            return UploadResponse(data)
        }
    }
}