package com.beetlestance.movies.ui

import android.os.Bundle
import com.beetlestance.movies.R
import com.beetlestance.movies.databinding.ActivityMainBinding
import com.beetlestance.movies.di.viewmodelfactory.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(R.layout.activity_main) {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}