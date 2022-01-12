package leandro.image.api.repository

import leandro.image.api.entity.Image
import org.springframework.web.multipart.MultipartFile

interface S3Repository {
    fun save(image : Image, file : MultipartFile) : Image
}