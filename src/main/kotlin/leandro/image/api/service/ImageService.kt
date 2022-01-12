package leandro.image.api.service

import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage

interface ImageService {
    fun resizeImage(sourceFile: MultipartFile): ArrayList<BufferedImage>
}