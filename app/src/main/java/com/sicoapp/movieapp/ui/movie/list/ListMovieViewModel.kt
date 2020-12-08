package com.sicoapp.movieapp.ui.movie.list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.topRated.AboveTopRated
import com.sicoapp.movieapp.data.response.topRated.Movie
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.CallbackFragmentViewModelAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/6/2020
 */
class ListMovieViewModel(callback: CallbackFragmentViewModelAdapter) : ViewModel() {
    private val topMoviesApiService: MovieApiService = ApiClient().getClient()!!.create(MovieApiService::class.java)
    private val callAllTopMovies = topMoviesApiService.getTopRatedMovies(API_KEY)


    private val callbackViewModelAdapter = object : CallbackFragmentViewModelAdapter {
        override fun onItemClicked(postID: Int) {
            callback.navigateToNextScren(postID)
        }
        override fun navigateToNextScren(postID: Int) {
        }
    }

    lateinit var _response :List<Movie>

    init {
        callAllTopMovies.enqueue(object : Callback<AboveTopRated> {
            override fun onResponse(
                call: Call<AboveTopRated>,
                response: Response<AboveTopRated>
            ) {
                 _response = response.body()?.results ?: return
                //topMovieAdapter.addMovies(response)
            }

            override fun onFailure(call: Call<AboveTopRated>, t: Throwable) {
                Log.d("mojkum", "onFailure ${t.localizedMessage}")
            }
        })
    }
    val topMovieAdapter = MovieAdapter(callback,_response)
}

