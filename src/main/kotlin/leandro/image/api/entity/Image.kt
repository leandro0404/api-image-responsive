package leandro.image.api.entity

import java.util.stream.Stream


class Image {
    var ucode: String = ""
    var id: String = ""
    var name: String = ""
    var size: Long = 0
    val resizeImages: ArrayList<ResizeImage> = arrayListOf()
    var extension: String = ""
    var processed: Boolean = false
}

class ResizeImage(var path: String, var width : Int , var height : Int , val size : Int)


enum class ImageSizesType(width: Int) {

    THUMB_512(512),
    THUMB_256(256),
    THUMB_128(128),
    THUMB_64(64);

    var width: Int = 0

    companion object {
        fun stream(): Stream<ImageSizesType> {
            return Stream.of(*values())
        }
    }

    init {
        this.width = width
    }
}


