package com.beetlestance.movies.data.models.response

import com.beetlestance.movies.data.models.entities.Movies
import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("page")
    val page: Page
)

data class Page(

    @SerializedName("page-num")
    val pageNum: String,

    @SerializedName("page-size")
    val pageSize: String,

    @SerializedName("content-items")
    val contentItems: ContentItems,

    @SerializedName("total-content-items")
    val totalContentItems: String,

    @SerializedName("title")
    val title: String
)

data class ContentItems(

    @SerializedName("content")
    val content: List<ContentItem>
) {
    fun toMovies() = content.map { contentItem ->
        Movies(name = contentItem.name, posterImage = contentItem.posterImage)
    }
}

data class ContentItem(

    @SerializedName("name")
    val name: String,

    @SerializedName("poster-image")
    val posterImage: String
)