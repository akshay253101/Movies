package com.beetlestance.movies.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.beetlestance.movies.R
import com.beetlestance.movies.databinding.ActivityMainBinding
import com.beetlestance.movies.di.viewmodelfactory.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main) {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    private fun requireBinding() = requireNotNull(binding)

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}