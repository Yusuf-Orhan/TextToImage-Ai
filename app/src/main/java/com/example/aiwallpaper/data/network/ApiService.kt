package com.example.aiwallpaper.data.network

import com.example.aiwallpaper.common.Constants.API_KEY
import com.example.aiwallpaper.data.model.TextToImageRequest
import com.example.aiwallpaper.data.model.TextToImageResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "authorization: Bearer $API_KEY"
    )
    @POST("v1/essential/text-to-image")
    suspend fun generateImage(@Body request: TextToImageRequest): TextToImageResponse
}