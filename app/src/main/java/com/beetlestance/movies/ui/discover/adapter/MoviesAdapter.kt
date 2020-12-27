package com.beetlestance.movies.ui.discover.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.beetlestance.movies.R
import com.beetlestance.movies.data.models.entities.Movies
import com.beetlestance.movies.databinding.ItemViewMovieBinding
import com.beetlestance.movies.utils.bindWithLayout

class MoviesAdapter : ListAdapter<Movies, MoviesAdapter.MoviesViewHolder>(DiffCallBack) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(bindWithLayout(R.layout.item_view_movie, parent))
    }

    override fun onBindViewHolder(holderMovies: MoviesViewHolder, position: Int) {
        holderMovies.binding.apply {
            movie = getItem(position)
            executePendingBindings()
        }
    }

    override fun getItemId(position: Int) = getItem(position).id

    inner class MoviesViewHolder(val binding: ItemViewMovieBinding) : ViewHolder(binding.root)

    object DiffCallBack : DiffUtil.ItemCallback<Movies>() {

        override fun areItemsTheSame(oldItem: Movies, newItem: Movies) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies) = oldItem == newItem
    }
}