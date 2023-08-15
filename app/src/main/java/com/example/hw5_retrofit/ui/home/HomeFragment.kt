package com.example.hw5_retrofit.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hw5_retrofit.MainApplication
import com.example.hw5_retrofit.R
import com.example.hw5_retrofit.common.viewBinding
import com.example.hw5_retrofit.data.model.GetBooksResponse
import com.example.hw5_retrofit.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home), BooksAdapter.BookListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val booksAdapter by lazy { BooksAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBooks.adapter = booksAdapter
        getBooks()
    }

    private fun getBooks(){
        MainApplication.bookService?.getBooks()?.enqueue(object : Callback<GetBooksResponse> {
            override fun onResponse(
                call: Call<GetBooksResponse>,
                response: Response<GetBooksResponse>
            ) {
                val result = response.body()?.books

                if(result.isNullOrEmpty().not()){
                    booksAdapter.submitList(result)
                }else{
                    //rv gone olsun, boş olduguna dair görsel ekle
                }
            }

            override fun onFailure(call: Call<GetBooksResponse>, t: Throwable) {
                    Log.e("GetBooks",t.message.orEmpty())
            }

        })
    }

    override fun onBookClicked(id: Int) {
        val action = HomeFragmentDirections.homeToDetail(id)
        findNavController().navigate(action)
    }
}