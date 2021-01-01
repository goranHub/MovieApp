package com.sicoapp.movieapp.ui.movie.popular

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.retrofitCallPopular
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.Adapter

/**
 * @author ll4
 * @date 12/6/2020
 */
class PopularViewModel(
    val postId: (Int) -> Unit,
    val crewID: (Int) -> Unit
) : ViewModel() {

    val adapter =
        Adapter(
            { it -> postId(it) },
            { it -> crewID(it) })

    var pageId = 1

    init {
        loadMovies(pageId)
    }

     fun loadMovies(pageId: Int) {
         retrofitCallPopular( pageId,
             {
                 adapter.addMovies(it)
             }
             ,
             {
                 Log.d(it, "onFailure if (response.isSuccessful) in MovieApiService ")
                 return@retrofitCallPopular true
             }
         )
    }
}


