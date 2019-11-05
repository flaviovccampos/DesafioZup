package br.com.codigozeroum.desafiozup.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class ImageUtils {

    companion object{

        fun bitmapToBase64(bitmap: Bitmap): String{
            val quality = 80
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.DEFAULT)
        }

        fun base64ToByteArray(base64: String): ByteArray{
            return Base64.decode(base64, Base64.DEFAULT)
        }
    }
}