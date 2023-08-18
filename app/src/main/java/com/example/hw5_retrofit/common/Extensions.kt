package com.example.hw5_retrofit.common

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.hw5_retrofit.R

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.GONE
}

fun ImageButton.favorited() {
    setImageResource(R.drawable.ic_favorite)
}

fun ImageButton.unFavorite() {
    setImageResource(R.drawable.ic_favorite_empty)
}

