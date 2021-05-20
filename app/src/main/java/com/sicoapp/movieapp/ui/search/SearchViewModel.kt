package com.sicoapp.movieapp.ui.search

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.remote.response.multi.Multi
import com.sicoapp.movieapp.domain.Repository
import com.sicoapp.movieapp.ui.search.adapter.SearchAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {

    fun searchForImage(query: String) = repository.getMulti(query)

}
