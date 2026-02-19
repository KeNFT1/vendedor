package com.vendedor.app.feature.camera.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoStorageManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val photosDir: File
        get() = File(context.filesDir, "photos").also { it.mkdirs() }

    private val thumbnailsDir: File
        get() = File(context.filesDir, "thumbnails").also { it.mkdirs() }

    suspend fun savePhoto(uri: Uri): String = withContext(Dispatchers.IO) {
        val fileName = "${UUID.randomUUID()}.jpg"
        val outputFile = File(photosDir, fileName)

        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw IllegalArgumentException("Cannot open URI: $uri")

        val originalBitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()

        val rotatedBitmap = rotateIfNeeded(uri, originalBitmap)
        val resizedBitmap = resizeBitmap(rotatedBitmap, MAX_DIMENSION)

        FileOutputStream(outputFile).use { out ->
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, out)
        }

        if (rotatedBitmap !== originalBitmap) rotatedBitmap.recycle()
        if (resizedBitmap !== rotatedBitmap) resizedBitmap.recycle()
        originalBitmap.recycle()

        generateThumbnail(outputFile.absolutePath)

        outputFile.absolutePath
    }

    suspend fun savePhotoFromFile(file: File): String = withContext(Dispatchers.IO) {
        val fileName = "${UUID.randomUUID()}.jpg"
        val outputFile = File(photosDir, fileName)

        val originalBitmap = BitmapFactory.decodeFile(file.absolutePath)
        val resizedBitmap = resizeBitmap(originalBitmap, MAX_DIMENSION)

        FileOutputStream(outputFile).use { out ->
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, out)
        }

        if (resizedBitmap !== originalBitmap) resizedBitmap.recycle()
        originalBitmap.recycle()

        generateThumbnail(outputFile.absolutePath)

        outputFile.absolutePath
    }

    fun getThumbnailPath(photoPath: String): String {
        val fileName = File(photoPath).name
        return File(thumbnailsDir, "thumb_$fileName").absolutePath
    }

    suspend fun deletePhoto(photoPath: String) = withContext(Dispatchers.IO) {
        File(photoPath).delete()
        File(getThumbnailPath(photoPath)).delete()
    }

    fun createTempPhotoFile(): File {
        return File.createTempFile("capture_", ".jpg", photosDir)
    }

    private fun generateThumbnail(photoPath: String) {
        val thumbFile = File(getThumbnailPath(photoPath))
        if (thumbFile.exists()) return

        val bitmap = BitmapFactory.decodeFile(photoPath)
        val thumbnail = resizeBitmap(bitmap, THUMBNAIL_DIMENSION)

        FileOutputStream(thumbFile).use { out ->
            thumbnail.compress(Bitmap.CompressFormat.JPEG, THUMBNAIL_QUALITY, out)
        }

        if (thumbnail !== bitmap) thumbnail.recycle()
        bitmap.recycle()
    }

    private fun resizeBitmap(bitmap: Bitmap, maxDimension: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        if (width <= maxDimension && height <= maxDimension) return bitmap

        val ratio = width.toFloat() / height.toFloat()
        val newWidth: Int
        val newHeight: Int

        if (width > height) {
            newWidth = maxDimension
            newHeight = (maxDimension / ratio).toInt()
        } else {
            newHeight = maxDimension
            newWidth = (maxDimension * ratio).toInt()
        }

        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }

    private fun rotateIfNeeded(uri: Uri, bitmap: Bitmap): Bitmap {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri) ?: return bitmap
            val exif = ExifInterface(inputStream)
            inputStream.close()

            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )

            val rotation = when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> 90f
                ExifInterface.ORIENTATION_ROTATE_180 -> 180f
                ExifInterface.ORIENTATION_ROTATE_270 -> 270f
                else -> return bitmap
            }

            val matrix = Matrix().apply { postRotate(rotation) }
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        } catch (_: Exception) {
            bitmap
        }
    }

    companion object {
        private const val MAX_DIMENSION = 1600
        private const val THUMBNAIL_DIMENSION = 300
        private const val JPEG_QUALITY = 85
        private const val THUMBNAIL_QUALITY = 75
    }
}
