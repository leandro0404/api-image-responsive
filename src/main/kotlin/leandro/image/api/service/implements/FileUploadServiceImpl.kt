package leandro.image.api.service.implements

import leandro.image.api.service.FileUploadService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.servlet.ServletContext


@Service
class FileUploadServiceImpl (
    private val context : ServletContext
        ) : FileUploadService {

    private val logger: Logger = LoggerFactory.getLogger(FileUploadServiceImpl::class.java)
    override fun upload(imageFile: MultipartFile?): File {
        val relativeWebPath = "/resources/images"
        val absoluteFilePath: String = context.getRealPath(relativeWebPath)
            val path: Path = Paths.get(absoluteFilePath, imageFile!!.originalFilename)

            Files.write(path, imageFile.bytes)
           return path.toFile()

    }
}