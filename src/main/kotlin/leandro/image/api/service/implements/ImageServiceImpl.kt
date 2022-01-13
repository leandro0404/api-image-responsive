package leandro.image.api.service.implements


import leandro.image.api.entity.ImageSizesType
import leandro.image.api.service.ImageService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO


@Service
class ImageServiceImpl : ImageService {


    override fun resizeImage(file: MultipartFile): ArrayList<BufferedImage> {
        val bufferedImage = ImageIO.read(file.inputStream)
        val images = generateResizes(bufferedImage)
        images.add(bufferedImage)
        return ArrayList(images.sortedByDescending { it.width })
    }


    private fun generateResizes(bufferedImage: BufferedImage): ArrayList<BufferedImage> {
        val images = ArrayList<BufferedImage>()
        val sizes = enumValues<ImageSizesType>()

        sizes.filter { x -> x.width < bufferedImage.width }.forEach { it ->

            val height = heightCalculator(bufferedImage.width.toDouble(),
                bufferedImage.height.toDouble(),
                it.width.toDouble())

            val resize = resizeImage(bufferedImage, it.width, height.toInt())

            images.add(resize);
        }
        return images
    }

    private fun heightCalculator(actualWidth: Double, actualHeight: Double, newWidth: Double): Double {

        val newWidthPercent = (newWidth * 100 / actualWidth)
        println(newWidthPercent)
        return actualHeight * (newWidthPercent / 100)
    }

    @Throws(IOException::class)
    fun resizeImage(originalImage: BufferedImage, targetWidth: Int, targetHeight: Int): BufferedImage {
        val resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT)
        val outputImage = BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB)
        outputImage.graphics.drawImage(resultingImage, 0, 0, null)
        return outputImage
    }


}