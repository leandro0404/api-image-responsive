package leandro.image.api.service.implements

import leandro.image.api.entity.ImageSizes
import leandro.image.api.service.ImageService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.awt.Image
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO
import kotlin.collections.ArrayList


@Service
class ImageServiceImpl : ImageService {


    override fun resizeImage(file: MultipartFile ): ArrayList<BufferedImage> {
        val images = ArrayList<BufferedImage>()

        val bufferedImage = ImageIO.read(file.inputStream)

        val sizes = enumValues<ImageSizes>()


          if ( bufferedImage.width > 256 ) {

               val height = heigthCalculator(bufferedImage.width.toDouble(),bufferedImage.height.toDouble(),256.0)

               val resize = resizeImage(bufferedImage,256,height.toInt())

               images.add(resize);
           }

        if (  bufferedImage.width > 128  ){
               val height = heigthCalculator(bufferedImage.width.toDouble(),bufferedImage.height.toDouble(),128.0)
               val resize = resizeImage(bufferedImage,128,height.toInt())
               images.add(resize);
           }

        if (  bufferedImage.width > 64  ) {
               val height = heigthCalculator(bufferedImage.width.toDouble(),bufferedImage.height.toDouble(),64.0)
               val resize = resizeImage(bufferedImage,64,height.toInt())
               images.add(resize);
           }





      return images
    }

    private fun heigthCalculator(altualWidth : Double,   alctualHeigth :Double, newWidth : Double) : Double
    {

        val newWidthPercent = (newWidth *100 /altualWidth )
        println(newWidthPercent)
        return alctualHeigth * (newWidthPercent / 100)
    }


    @Throws(IOException::class)
    fun resizeImage(originalImage: BufferedImage, targetWidth: Int, targetHeight: Int): BufferedImage {
        val resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT)
        val outputImage = BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB)
        outputImage.graphics.drawImage(resultingImage, 0, 0, null)
        return outputImage
    }


}