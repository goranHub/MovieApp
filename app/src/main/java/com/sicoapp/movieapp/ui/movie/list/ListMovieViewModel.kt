package com.sicoapp.movieapp.ui.movie.list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.retrofitCallList
import com.sicoapp.movieapp.ui.movie.list.adapter.ListMovieAdapter

/**
 * @author ll4
 * @date 12/6/2020
 */
class ListMovieViewModel(
    val postId: (Int) -> Unit,
    val crewID: (Int) -> Unit
) : ViewModel() {

    // iz adaptera id stavljamo u postId od ListMovieViewModel
    val adapter = ListMovieAdapter({ it -> postId(it) }, { it -> crewID(it) })
    var pageId = 1

    init {
        loadMovies(pageId)
    }

     fun loadMovies(pageId: Int) {
         retrofitCallList( pageId,
             {  //it List<ListItemViewModel>
                //onSuccess: (movies: List<ListItemViewModel>) -> Unit,
                 adapter.addMovies(it)
             },{
                 Log.d(it, "onFailure if (response.isSuccessful) in MovieApiService ")
                 return@retrofitCallList true
             }
         )
    }
}


