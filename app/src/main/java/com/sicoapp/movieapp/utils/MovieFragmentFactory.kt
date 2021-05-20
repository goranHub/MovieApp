package com.sicoapp.movieapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.sicoapp.movieapp.ui.popular.PopularMovieFragment
import com.sicoapp.movieapp.ui.popular.adapter.PopularMovieAdapter
import com.sicoapp.movieapp.ui.topmovie.TopMovieFragment
import com.sicoapp.movieapp.ui.topmovie.adapter.TopMovieAdapter
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    private val popularAdapter : PopularMovieAdapter,
    private val topMovieAdapter : TopMovieAdapter,
): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            PopularMovieFragment::class.java.name -> PopularMovieFragment(popularAdapter)
            TopMovieFragment::class.java.name -> TopMovieFragment(topMovieAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}