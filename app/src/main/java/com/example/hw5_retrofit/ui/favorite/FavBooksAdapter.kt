package com.example.hw5_retrofit.ui.favorite


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw5_retrofit.common.favorited
import com.example.hw5_retrofit.common.loadImage
import com.example.hw5_retrofit.common.unFavorite
import com.example.hw5_retrofit.data.source.local.FavBook
import com.example.hw5_retrofit.databinding.ItemBookBinding

class FavBooksAdapter(
    private val favBooksListener: FavBooksListener
) : ListAdapter<FavBook, FavBooksAdapter.FavBooksViewHolder>(FavBookDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavBooksViewHolder =
        FavBooksViewHolder(
            ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FavBooksViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class FavBooksViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favBook: FavBook) {
            with(binding) {

                btnFavorite.favorited()


                tvTitle.text = favBook.name
                tvPrice.text = "${favBook.price} $"

                ivBook.loadImage(favBook.bookImage)


                btnFavorite.setOnClickListener {
                    btnFavorite.unFavorite()
                    favBooksListener.onFavClicked(false, favBook)
                }

                root.setOnClickListener {
                    favBooksListener.onBookClicked(favBook.bookId ?: 1)
                }

            }
        }
    }

    class FavBookDiffCallback : DiffUtil.ItemCallback<FavBook>() {
        override fun areItemsTheSame(oldItem: FavBook, newItem: FavBook): Boolean {
            return oldItem.bookId == newItem.bookId
        }

        override fun areContentsTheSame(oldItem: FavBook, newItem: FavBook): Boolean {
            return oldItem == newItem
        }

    }

    interface FavBooksListener {
        fun onBookClicked(id: Int)

        fun onFavClicked(isFavorited: Boolean, favBook: FavBook)
    }
}