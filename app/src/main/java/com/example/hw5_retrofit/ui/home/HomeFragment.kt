package com.example.hw5_retrofit.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hw5_retrofit.MainApplication
import com.example.hw5_retrofit.R
import com.example.hw5_retrofit.common.invisible
import com.example.hw5_retrofit.common.viewBinding
import com.example.hw5_retrofit.common.visible
import com.example.hw5_retrofit.data.model.Book
import com.example.hw5_retrofit.data.model.GetBooksResponse
import com.example.hw5_retrofit.data.source.local.FavBook
import com.example.hw5_retrofit.data.source.local.FavBookDb
import com.example.hw5_retrofit.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home), BooksAdapter.BookListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val booksAdapter by lazy { BooksAdapter(this) }

    private var favBookDb: FavBookDb? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favBookDb = FavBookDb.getFavBookDb(requireContext())

        binding.rvBooks.adapter = booksAdapter

        getBooks()

    }

    private fun getBooks() {
        MainApplication.bookService?.getBooks()?.enqueue(object : Callback<GetBooksResponse> {
            override fun onResponse(
                call: Call<GetBooksResponse>,
                response: Response<GetBooksResponse>
            ) {
                val result = response.body()?.books

                if (result.isNullOrEmpty().not()) {

                    result!!.map {
                        Book(
                            it.author,
                            it.id,
                            it.imageUrl,
                            it.isBestSeller,
                            it.name,
                            it.price,
                            it.publisher,
                            favBookDb?.favBookDao()?.getNamesFavBooks()?.contains(it.name) == true
                        )

                    }.also {
                        booksAdapter.submitList(it)
                    }

                    binding.radioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                        when (checkedId) {
                            binding.rbSortBest.id -> booksAdapter.submitList(result.filter { it.isBestSeller == true }
                                .sortedBy { it.price })

                            binding.rbSortPrice.id ->
                                booksAdapter.submitList(result.sortedBy { it.price })

                            else -> radioGroup.clearCheck()

                        }
                    }

//                    binding.rvBooks.let {
//                        it.smoothScrollToPosition(it.bottom)
//                    }



                } else {
                    with(binding) {
                        rvBooks.invisible()
                        tvEmptyList.visible()
                    }
                }
            }

            override fun onFailure(call: Call<GetBooksResponse>, t: Throwable) {
                Log.e("GetBooks", t.message.orEmpty())
            }

        })
    }

    override fun onBookClicked(id: Int) {
        val action = HomeFragmentDirections.homeToDetail(id)
        findNavController().navigate(action)
    }

    override fun onFavClicked(isFavorited: Boolean, book: Book) {
        val favBook = FavBook(book.id, book.name, book.publisher, book.price, book.imageUrl)

        if (isFavorited) {
            favBookDb?.favBookDao()?.insert(favBook)
            Toast.makeText(requireContext(), "Favorilere Eklendi.", Toast.LENGTH_SHORT).show()
        } else {
            favBookDb?.favBookDao()?.delete(favBook)
            Toast.makeText(requireContext(), "Favorilerden Çıkarıldı.", Toast.LENGTH_SHORT).show()
        }
    }

}