package leandro.image.api.repository

import leandro.image.api.entity.Image
import leandro.image.api.entity.ResizeImage
import org.springframework.web.multipart.MultipartFile

interface S3Repository {

    fun save(image : Image, file : MultipartFile)
}