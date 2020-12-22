package com.sicoapp.movieapp.ui.movie.list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.AboveTopRated
import com.sicoapp.movieapp.ui.movie.list.adapter.ListMovieAdapter
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/6/2020
 */
class ListMovieViewModel(val postId: (Int) -> Unit, val crewID: (Int) -> Unit) :
    ViewModel() {

    // iz adaptera id stavljamo u postId od ListMovieViewModel
    val adapter = ListMovieAdapter({ it -> postId(it) }, { it -> crewID(it) })
    var pageId = 1

    init {
        loadMovies(pageId)
    }

     fun loadMovies(pageId: Int) {
        val topMoviesApiService =
            ApiClient().getClient()?.create(MovieApiService::class.java) ?: return
        val callAllTopMovies = topMoviesApiService.getTopRatedMovies(API_KEY, pageId.toString())

        callAllTopMovies.enqueue(object : Callback<AboveTopRated> {
            override fun onResponse(
                call: Call<AboveTopRated>,
                response: Response<AboveTopRated>
            ) {
                val moviesList = response.body()?.results ?: return
                val movieItemsList = moviesList.map { ListItemViewModel(it) }
                adapter.addMovies(movieItemsList)

            }

            override fun onFailure(call: Call<AboveTopRated>, t: Throwable) {
                Log.d("mojkum", "onFailure ${t.localizedMessage}")
            }
        })

    }


}


