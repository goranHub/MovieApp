package com.sicoapp.movieapp.ui.popular

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.databinding.ItemMoviePopularBinding
import com.sicoapp.movieapp.domain.Repository

/**
 * @author ll4
 * @date 12/6/2020
 */
class PopularViewModel @ViewModelInject constructor (
    private val repository: Repository,
) : ViewModel() {

    var pageId = 1L

    lateinit var callback : (ItemMoviePopularBinding) -> Unit

     fun getPopular(pageId :Long) = repository.getPopular(pageId)


}


