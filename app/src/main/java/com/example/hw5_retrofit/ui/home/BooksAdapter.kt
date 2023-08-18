package com.example.hw5_retrofit.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw5_retrofit.common.favorited
import com.example.hw5_retrofit.common.loadImage
import com.example.hw5_retrofit.common.unFavorite
import com.example.hw5_retrofit.common.visible
import com.example.hw5_retrofit.data.model.Book
import com.example.hw5_retrofit.databinding.ItemBookBinding

class BooksAdapter(
    private val bookListener: BookListener
) : ListAdapter<Book, BooksAdapter.BookViewHolder>(BookDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(
            ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(getItem(position))


    inner class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            with(binding) {


                if (book.isFav) {
                    btnFavorite.favorited()
                }

                tvTitle.text = book.name
                tvPrice.text = "${book.price} $"

                ivBook.loadImage(book.imageUrl)


                btnFavorite.setOnClickListener {
                    if (book.isFav) {
                        book.isFav = false
                        btnFavorite.unFavorite()
                        bookListener.onFavClicked(false, book)
                    } else {
                        book.isFav = true
                        btnFavorite.favorited()
                        bookListener.onFavClicked(true, book)
                    }
                }

                if (book.isBestSeller == true) {
                    ivBestSeller.visible()
                }

                root.setOnClickListener {
                    bookListener.onBookClicked(book.id ?: 1)
                }

            }
        }
    }


    class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }


    interface BookListener {
        fun onBookClicked(id: Int)

        fun onFavClicked(isFavorited: Boolean, book: Book)

    }
}