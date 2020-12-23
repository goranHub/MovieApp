package com.sicoapp.movieapp.ui.movie.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sicoapp.movieapp.data.api.MovieApiService
import java.lang.IllegalArgumentException

/**
 * @author ll4
 * @date 12/19/2020
 */
class DetailViewModelFactory(private val itemId :Int, private val service: MovieApiService?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            return  DetailsViewModel(itemId, service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}