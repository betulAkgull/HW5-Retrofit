package com.example.hw5_retrofit.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hw5_retrofit.R
import com.example.hw5_retrofit.common.viewBinding
import com.example.hw5_retrofit.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

        }
    }
}