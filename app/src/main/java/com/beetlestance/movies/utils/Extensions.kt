package com.beetlestance.movies.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.beetlestance.movies.constants.PLACEHOLDER_ASSET
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okio.IOException
import java.io.FileNotFoundException
import java.nio.charset.StandardCharsets.UTF_8

fun Context.loadJSONFromAsset(jsonFileName: String): String? {
    return try {
        assets.open(jsonFileName).let { inputStream ->
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, UTF_8)
        }
    } catch (e: IOException) {
        null
    }
}

fun Context.loadBitmapFromAsset(fileName: String): Bitmap? {
    return try {
        assets.open(fileName).let { inputStream ->
            BitmapFactory.decodeStream(inputStream).also {
                inputStream.close()
            }
        }
    } catch (e: IOException) {
        if (e is FileNotFoundException && fileName != PLACEHOLDER_ASSET) {
            loadBitmapFromAsset(PLACEHOLDER_ASSET)
        } else null
    }
}

fun <T> T.toJsonString(): String {
    return Gson().toJson(this)
}

inline fun <reified T> String.toDataClass(): T {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}

inline fun <T : ViewDataBinding> Fragment.bindWithLifecycleOwner(
    bind: (T.() -> Unit) = {}
): T {
    val binding: T = checkNotNull(DataBindingUtil.bind(requireView()))
    binding.lifecycleOwner = viewLifecycleOwner
    binding.bind()
    return binding
}

inline fun <T : ViewDataBinding> bindWithLayout(
    @LayoutRes layoutId: Int,
    parent: ViewGroup,
    attachToRoot: Boolean = false,
    bind: (T.() -> Unit) = {}
): T {
    val inflater: LayoutInflater = LayoutInflater.from(parent.context)
    val binding: T = DataBindingUtil.inflate(inflater, layoutId, parent, attachToRoot)
    binding.bind()
    return binding
}
