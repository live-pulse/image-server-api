package site.hannahlog.cdn.image.cdn.dto.request

enum class UploadPath {
    LIVE_PULSE, TEST;

    companion object {
        fun of(path: UploadPath): String {
            return when (path) {
                LIVE_PULSE -> "live-pulse"
                TEST -> "test"
            }
        }
    }

}