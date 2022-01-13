package leandro.image.api.controller

import leandro.image.api.useCase.UploadImageUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController("image")
class ImageController
    ( private val uploadImageUseCase : UploadImageUseCase

){

    @PostMapping("/upload")
    fun upload(@RequestParam("image") imageFile: MultipartFile) {
        val result = uploadImageUseCase.execute(imageFile)
    }
}
