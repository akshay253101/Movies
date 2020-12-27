package com.beetlestance.movies.ui.discover.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.beetlestance.movies.R
import com.beetlestance.movies.data.models.entities.Movies
import com.beetlestance.movies.databinding.ItemViewMovieBinding
import com.beetlestance.movies.utils.bindWithLayout

class MoviesAdapter : PagingDataAdapter<Movies, MoviesAdapter.MoviesViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(bindWithLayout(R.layout.item_view_movie, parent))
    }

    override fun onBindViewHolder(holderMovies: MoviesViewHolder, position: Int) {
        holderMovies.binding.apply {
            movie = getItem(position)
            executePendingBindings()
        }
    }

    inner class MoviesViewHolder(val binding: ItemViewMovieBinding) : ViewHolder(binding.root)

    object DiffCallBack : DiffUtil.ItemCallback<Movies>() {

        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }
    }
}