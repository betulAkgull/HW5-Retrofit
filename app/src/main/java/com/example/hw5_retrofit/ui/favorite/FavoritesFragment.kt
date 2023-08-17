package com.example.hw5_retrofit.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hw5_retrofit.R
import com.example.hw5_retrofit.common.viewBinding
import com.example.hw5_retrofit.data.model.Book
import com.example.hw5_retrofit.data.source.local.FavBook
import com.example.hw5_retrofit.data.source.local.FavBookDb
import com.example.hw5_retrofit.databinding.FragmentFavoritesBinding
import com.example.hw5_retrofit.ui.home.BooksAdapter

class FavoritesFragment : Fragment(R.layout.fragment_favorites), FavBooksAdapter.FavBooksListener {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    private val favBooksAdapter by lazy { FavBooksAdapter(this) }

    private var favBookDb: FavBookDb? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favBookDb = FavBookDb.getFavBookDb(requireContext())

        binding.rvFavorites.adapter = favBooksAdapter
        getFavoritedBooks()
    }

    fun getFavoritedBooks() {

        favBookDb?.favBookDao()?.getAllFavBooks()?.let {
            favBooksAdapter.submitList(it)
        }

    }


    override fun onBookClicked(id: Int) {
        val action = FavoritesFragmentDirections.favoritesToDetail(id)
        findNavController().navigate(action)
    }

    override fun onFavClicked(isFavorited: Boolean, favBook: FavBook) {
        if (isFavorited) {
            favBookDb?.favBookDao()?.insert(favBook)
            Toast.makeText(requireContext(), "Favorilere Eklendi.", Toast.LENGTH_SHORT).show()
        } else {
            favBookDb?.favBookDao()?.delete(favBook)
            Toast.makeText(requireContext(), "Favorilerden Çıkarıldı.", Toast.LENGTH_SHORT).show()
        }
    }


}