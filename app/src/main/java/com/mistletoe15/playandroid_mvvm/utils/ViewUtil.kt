package com.mistletoe15.playandroid_mvvm.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Mistletoe on 2020/6/2
 **/
object ViewUtil {
    fun getHttpBitmap(url:String?): Bitmap?{
        val myFileURL: URL
        var bitmap: Bitmap? = null
        try {
            myFileURL = URL(url)
            (myFileURL.openConnection() as HttpURLConnection).apply {
                connectTimeout = 6000
                doInput = true
                useCaches = false
            }.let {
                val inputStream: InputStream = it.inputStream
                bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }
}