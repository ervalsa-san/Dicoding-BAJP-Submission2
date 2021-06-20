package com.ervalsa.tmdb.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object Helper {

    const val TYPE_MOVIE = "TYPE_MOVIE"
    const val TYPE_TVSHOW = "TYPE_TVSHOW"
    const val IMAGE_API_ENDPOINT = "https://image.tmdb.org/t/p/"
    const val POSTER_SIZE_W185_ENDPOINT = "w185"
    const val POSTER_SIZE_W780_ENDPOINT = "w780"

    fun setImageWithGlide(context: Context, imagePath: String, imageView: ImageView) {
        Glide.with(context).clear(imageView)
        Glide.with(context).load(imagePath).into(imageView)
    }
}