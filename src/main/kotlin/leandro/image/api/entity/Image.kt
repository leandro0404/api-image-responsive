package leandro.image.api.entity

import java.util.*


class Image {
    var ucode: String = ""
    var id: String = ""
    var name: String = ""
    var size: Long = 0
    val resizeImages: ArrayList<ResizeImage> = arrayListOf()
    var extension: String = ""
    var processed: Boolean = false
}

class ResizeImage {
    val width: Int = 0
    val heigth: Int = 0
    val path: String = ""
}
enum class ImageSizes(val width: Int) {
    THUMB_256(256),
    THUMB_128(128),
    THUMB_64(64);

    companion object {
        fun fromWidth(width: Int): Optional<ImageSizes> {
            return Arrays.stream(values())
                .filter { bl -> bl.width == width }
                .findFirst()
        }
        fun fromName(name: String): Optional<ImageSizes> {
            return Arrays.stream(values())
                .filter { bl -> bl.name == name }
                .findFirst()
        }
    }
}



