package com.beetlestance.movies.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("loadAsset")
fun loadAsset(
    imageView: AppCompatImageView,
    assetName: String
) {
    val bitmap = imageView.context.loadBitmapFromAsset(assetName)
    imageView.load(bitmap = bitmap) {
        crossfade(true)
    }
}