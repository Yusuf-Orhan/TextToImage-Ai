package com.example.aiwallpaper.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiwallpaper.data.model.TextToImageRequest
import com.example.aiwallpaper.data.model.TextToImageResponse
import com.example.aiwallpaper.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api : ApiService
) : ViewModel() {
    val state = MutableLiveData<MainState>(MainState(image = "", error = "",isLoading = false))

    fun generateImage(prompt : String) = viewModelScope.launch {
        state.value = state.value?.copy(isLoading = true)
        try {
            val response = api.generateImage(TextToImageRequest(prompt = prompt))
            state.value = state.value?.copy(isLoading = false, image = response.image)
        }catch (e: Exception) {
            state.value = state.value?.copy(isLoading = false, error = e.message!!)
            println("MainViewModel Error : ${e.message}")
        }
        /*response.enqueue(object : Callback<TextToImageResponse> {
            override fun onResponse(call: Call<TextToImageResponse>, response: Response<TextToImageResponse>) {
            }

            override fun onFailure(call: Call<TextToImageResponse>, t: Throwable) {
                state.value = state.value?.copy(isLoading = false, error = t.message)
                println("MainViewModel Error : ${t.message}")
            }

        })

         */
    }
}




data class MainState(
    val image : String,
    val error : String,
    val isLoading : Boolean
)