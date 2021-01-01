package com.sicoapp.movieapp.ui.movie.topmovie

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.retrofitCallTopRated
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.Adapter

/**
 * @author ll4
 * @date 12/6/2020
 */
class TopMovieViewModel(
    val postId: (Int) -> Unit,
    val crewID: (Int) -> Unit
) : ViewModel() {

    // iz adaptera id stavljamo u postId od ListMovieViewModel

    val adapter =
        Adapter(
            { it -> postId(it) },
            { it -> crewID(it) })

    var pageId = 1

    init {
        loadMovies(pageId)
    }

     fun loadMovies(pageId: Int) {
         retrofitCallTopRated( pageId,
             {  //it List<ListItemViewModel>
                //onSuccess: (movies: List<ListItemViewModel>) -> Unit,
                 adapter.addMovies(it)
             }
             ,
             {
                 Log.d(it, "onFailure if (response.isSuccessful) in MovieApiService ")
                 return@retrofitCallTopRated true
             }
         )
    }
}


