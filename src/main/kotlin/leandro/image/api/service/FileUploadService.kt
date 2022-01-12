package leandro.image.api.service

import org.springframework.web.multipart.MultipartFile
import java.io.File


interface FileUploadService {
    fun upload(imageFile: MultipartFile?): File
}