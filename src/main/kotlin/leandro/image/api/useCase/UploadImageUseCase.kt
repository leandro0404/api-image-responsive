package leandro.image.api.useCase

import leandro.image.api.core.usecase.UseCaseRequestResponse
import leandro.image.api.dto.ImageResponse
import leandro.image.api.entity.Image
import leandro.image.api.repository.S3Repository
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class UploadImageUseCase
    (
    private val s3Repository: S3Repository,
) : UseCaseRequestResponse<MultipartFile, ImageResponse>() {

    override fun onExecute(request: MultipartFile): ImageResponse {

        val image = Image()
        image.ucode = UUID.randomUUID().toString()
        image.id = UUID.randomUUID().toString()
        image.name = request.originalFilename.toString()
        image.extension = request.contentType.toString()
        image.size = request.size
        image.processed = true
        s3Repository.save(image, request);

        return ImageResponse()
    }
}