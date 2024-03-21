package com.example.aiwallpaper.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.aiwallpaper.common.toBitmap
import com.example.aiwallpaper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            button.setOnClickListener {
                viewModel.generateImage(editTextText.text.toString())
            }
        }
        observes()
    }
    private fun observes() = with(binding) {
        viewModel.state.observe(this@MainActivity) { state ->
            if(state.isLoading) {
                progressBar.visibility = View.VISIBLE
            }else if (state.error.isNotBlank()) {
                progressBar.visibility = View.GONE
                Toast.makeText(applicationContext,state.error,Toast.LENGTH_SHORT).show()
            }else if (state.image.isNotBlank()) {
                progressBar.visibility = View.GONE
                imageView.setImageBitmap(state.image.toBitmap())
            }
        }
    }
}

