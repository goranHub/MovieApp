package com.sicoapp.movieapp.ui.movie.topmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sicoapp.movieapp.repository.RemoteRepository
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/7/2021
 */
class TopMovieViewModelFactory @Inject constructor(

    private val remoteRepository: RemoteRepository

) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopMovieViewModel(remoteRepository) as T
    }

}