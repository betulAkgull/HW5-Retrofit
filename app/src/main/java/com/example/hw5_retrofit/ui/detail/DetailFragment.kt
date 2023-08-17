package com.example.hw5_retrofit.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.hw5_retrofit.MainApplication
import com.example.hw5_retrofit.R
import com.example.hw5_retrofit.common.favorited
import com.example.hw5_retrofit.common.loadImage
import com.example.hw5_retrofit.common.unFavorite
import com.example.hw5_retrofit.common.viewBinding
import com.example.hw5_retrofit.data.model.GetBookDetailResponse
import com.example.hw5_retrofit.data.model.GetBooksResponse
import com.example.hw5_retrofit.data.source.local.FavBookDb
import com.example.hw5_retrofit.databinding.FragmentDetailBinding
import com.example.hw5_retrofit.ui.home.BooksAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val args by navArgs<DetailFragmentArgs>()

    private var favBookDb: FavBookDb? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favBookDb = FavBookDb.getFavBookDb(requireContext())
        getBookDetail(args.id)
    }

    private fun getBookDetail(id: Int) {
        MainApplication.bookService?.getBookDetail(id)
            ?.enqueue(object : Callback<GetBookDetailResponse> {
                override fun onResponse(
                    call: Call<GetBookDetailResponse>,
                    response: Response<GetBookDetailResponse>
                ) {
                    val result = response.body()?.book

                    if (result != null) {
                        with(binding) {

                            tvAuthor.text = result.author
                            ivBook.loadImage(result.imageUrl)
                            tvPublisher.text = result.publisher
                            tvTitle.text = result.name
                            tvPrice.text = result.price.toString()

                        }
                    }
                }

                override fun onFailure(call: Call<GetBookDetailResponse>, t: Throwable) {
                    Log.e("GetBooks", t.message.orEmpty())
                }

            })
    }


}