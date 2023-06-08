package com.example.cookbook.domain.cache

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.example.cookbook.domain.entity.entity_categories.Category
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class SaveURLtoFile {
    var bitmap: Bitmap? = null
    var outputStream: OutputStream? = null

    @SuppressLint("SuspiciousIndentation")
    fun saveInFile(category: Category): String {
        while (category.strCategoryThumb == null) {
        }
        bitmap = getBitmapFromURL(category.strCategoryThumb)
        return saveImage(bitmap, category.strCategory)
    }

    private fun saveImage(bitmap: Bitmap?, nameCourse: String): String {

        val dir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES + "/Cookbook"
        )

        val file = File(dir, "course_" + nameCourse + ".jpg")

        try {
            if (!dir.exists()) {
                dir.mkdir()
            }
            outputStream = FileOutputStream(file)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        try {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        try {
            outputStream!!.flush()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        try {
            outputStream!!.close()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return file.path
    }

    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}