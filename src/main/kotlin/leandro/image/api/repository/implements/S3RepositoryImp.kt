package leandro.image.api.repository.implements

import com.google.gson.Gson
import leandro.image.api.entity.Image
import leandro.image.api.repository.S3Repository
import leandro.image.api.service.ImageService
import org.springframework.stereotype.Repository
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.imageio.ImageIO





@Repository
class S3RepositoryImp
    (
    private val s3Client: S3Client,
    private val imageService: ImageService,
) : S3Repository {

    companion object {
        private const val bucketName = "user-acess"
        private const val contentType = "application/json"
        private val gson = Gson()
    }


    override fun save(image: Image, file: MultipartFile): Image {

        val jsonString = gson.toJson(image)
        s3Client.putObject(createPutObjectRequest(image.ucode + "/" + image.id), RequestBody.fromString(jsonString));
        saveFile(image, file)

        return image
    }

    private fun saveFile(image: Image, file: MultipartFile) {


        val resizes =  imageService.resizeImage(file)

        resizes.forEach { it ->

            val request = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(image.ucode + "/" + image.id + "/img/" + it.width + image.name)
                .contentType(file.contentType)
                .build()


            val image = ByteArrayOutputStream()
           val  i = ImageIO.write(it, "jpg", image)
            val bytes: ByteArray = image.toByteArray()

            val convFile = File(file.originalFilename)
            val fos = FileOutputStream(convFile)
            fos.write(bytes)
            fos.close()

            s3Client.putObject(request, RequestBody.fromInputStream(convFile.inputStream(), convFile.length()));
        }


    }

    private fun createPutObjectRequest(key: String): PutObjectRequest {
        return PutObjectRequest
            .builder()
            .bucket(bucketName)
            .key(key)
            .contentType(contentType)
            .build()
    }


}