package com.sicoapp.movieapp.utils.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.crew.CrewViewModel
import com.sicoapp.movieapp.ui.movie.popular.PopularViewModel
import com.sicoapp.movieapp.ui.movie.search.SearchViewModel
import com.sicoapp.movieapp.ui.movie.topmovie.TopMovieViewModel

class ViewModelFactory(
    private val remoteRepository: RemoteRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TopMovieViewModel::class.java)) {
            return TopMovieViewModel(remoteRepository) as T
        }
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(remoteRepository) as T
        }
        if (modelClass.isAssignableFrom(PopularViewModel::class.java)) {
            return PopularViewModel(remoteRepository) as T
        }

        if (modelClass.isAssignableFrom(CrewViewModel::class.java)) {
            return CrewViewModel(remoteRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
