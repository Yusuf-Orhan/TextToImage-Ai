package com.example.aiwallpaper.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayInputStream

fun String.toBitmap(): Bitmap? {
    val decodedBytes = Base64.decode(this, Base64.DEFAULT)
    val inputStream = ByteArrayInputStream(decodedBytes)
    return BitmapFactory.decodeStream(inputStream)
}