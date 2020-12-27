package com.beetlestance.movies.utils

import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("loadAsset")
fun loadAsset(imageView: AppCompatImageView, assetName: String?) {
    val bitmap = imageView.context.loadBitmapFromAsset(assetName ?: return)
    imageView.load(bitmap = bitmap) {
        crossfade(true)
    }
}


@BindingAdapter("imeAction")
fun setImeActions(view: AppCompatEditText, imeAction: () -> Unit) {
    view.imeOptions = EditorInfo.IME_ACTION_SEARCH
    view.setOnEditorActionListener { v, _, _ ->
        val hasValidInput = v.text.length > 3
        if (hasValidInput) imeAction()
        true
    }
}