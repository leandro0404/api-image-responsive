package leandro.image.api.controller

import leandro.image.api.dto.ImageResponse
import leandro.image.api.service.FileUploadService
import leandro.image.api.service.ImageService
import leandro.image.api.useCase.UploadImageUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import reactor.kotlin.core.publisher.toMono
import java.io.File


@RestController("image")
class ImageController
    ( private val uploadImageUseCase : UploadImageUseCase

){
    @GetMapping
    fun get(): Boolean = true

    @PostMapping("/uploadImage")
    fun uploadImage(@RequestParam("image") imageFile: MultipartFile) {


        val result = uploadImageUseCase.execute(imageFile)


    }



}
