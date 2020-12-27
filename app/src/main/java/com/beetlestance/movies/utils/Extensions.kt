package com.beetlestance.movies.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okio.IOException
import java.nio.charset.StandardCharsets.UTF_8

fun Context.loadJSONFromAsset(jsonFileName: String): String? {
    return try {
        assets.open(jsonFileName).let {
            val buffer = ByteArray(it.available())
            it.read(buffer)
            it.close()
            String(buffer, UTF_8)
        }
    } catch (e: IOException) {
        null
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
