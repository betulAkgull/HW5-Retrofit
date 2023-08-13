package com.example.hw5_retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw5_retrofit.common.viewBinding
import com.example.hw5_retrofit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}