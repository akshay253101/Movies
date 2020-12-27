package com.beetlestance.movies.utils

import android.content.Context
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