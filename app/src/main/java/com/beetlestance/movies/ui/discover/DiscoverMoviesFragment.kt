package com.beetlestance.movies.ui.discover

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.beetlestance.movies.R
import com.beetlestance.movies.databinding.FragmentDiscoverMoviesBinding
import com.beetlestance.movies.di.viewmodelfactory.ViewModelFactory
import com.beetlestance.movies.ui.discover.adapter.MoviesAdapter
import com.beetlestance.movies.utils.bindWithLifecycleOwner
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class DiscoverMoviesFragment : DaggerFragment(R.layout.fragment_discover_movies) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: DiscoverMoviesViewModel by viewModels { viewModelFactory }

    private var moviesAdapter: MoviesAdapter? = null

    private var binding: FragmentDiscoverMoviesBinding? = null

    private fun requireBinding() = requireNotNull(binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bindWithLifecycleOwner {
            discoverMoviesViewModel = viewModel
        }
        initRecyclerView()
        setDataObserver()
        setViewListeners()
    }

    private fun initRecyclerView() {
        moviesAdapter = MoviesAdapter()
        val childCount = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> 7
            else -> 3
        }
        requireBinding().fragmentDiscoverMoviesRecyclerView.apply {
            adapter = moviesAdapter
            (layoutManager as? GridLayoutManager)?.spanCount = childCount
        }
    }

    private fun setDataObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.movies.collectLatest {
                moviesAdapter?.submitData(it)
            }
        }

        viewModel.searchQuery.observe(viewLifecycleOwner) { viewModel.executeQuery() }
    }

    private fun setViewListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (requireBinding().rootFragmentDiscoverMovies.progress != 0f) {
                requireBinding().rootFragmentDiscoverMovies.transitionToStart()
            } else {
                isEnabled = false
                requireActivity().onBackPressed()
            }
        }

        requireBinding().fragmentDiscoverMoviesNavigationIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }

        requireBinding().fragmentDiscoverMoviesOpenSearchView.setOnClickListener {
            if (viewModel.searchQuery.value.isNullOrBlank()) {
                requireBinding().rootFragmentDiscoverMovies.transitionToEnd()
            } else {
                viewModel.searchQuery.value = ""
            }
        }

        requireBinding().fragmentDiscoverMoviesEditLayout.setEndIconOnClickListener {
            requireBinding().rootFragmentDiscoverMovies.transitionToStart()
        }
    }

    override fun onDestroyView() {
        binding = null
        moviesAdapter = null
        super.onDestroyView()
    }

}