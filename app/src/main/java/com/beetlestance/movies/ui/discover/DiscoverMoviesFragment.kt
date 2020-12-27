package com.beetlestance.movies.ui.discover

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.beetlestance.movies.R
import com.beetlestance.movies.databinding.FragmentDiscoverMoviesBinding
import com.beetlestance.movies.di.viewmodelfactory.ViewModelFactory
import com.beetlestance.movies.utils.bindWithLifecycleOwner
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DiscoverMoviesFragment : DaggerFragment(R.layout.fragment_discover_movies) {

    private var binding: FragmentDiscoverMoviesBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: DiscoverMoviesViewModel by viewModels { viewModelFactory }

    private fun requireBinding() = requireNotNull(binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bindWithLifecycleOwner {
            discoverMoviesViewModel = viewModel
        }

        setViewListeners()
    }

    private fun setViewListeners() {
        requireBinding().fragmentDiscoverMoviesToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}