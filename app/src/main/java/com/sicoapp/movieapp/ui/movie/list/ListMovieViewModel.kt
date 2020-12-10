package com.sicoapp.movieapp.ui.movie.list

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.topRated.AboveTopRated
import com.sicoapp.movieapp.data.response.topRated.Movie
import com.sicoapp.movieapp.ui.movie.list.newadapter.MyRecyclerViewAdapter
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/6/2020
 */
class ListMovieViewModel(val postId: (Int) -> Unit, context: Context, var listData: List<Movie>) : ViewModel() {
    private val topMoviesApiService: MovieApiService = ApiClient().getClient()!!.create(MovieApiService::class.java)
    private val callAllTopMovies = topMoviesApiService.getTopRatedMovies(API_KEY)


    val topMovieAdapter = MovieAdapter{it ->
        postId(it)
    }


    val myRecyclerViewAdapter = MyRecyclerViewAdapter({ it ->
        postId(it) }, fetchMovies(listData), context)


    private fun fetchMovies(listData : List<Movie>) : List<Movie>{
       // lateinit var lista : List<Movie>
        callAllTopMovies.enqueue(object : Callback<AboveTopRated> {
            override fun onResponse(
                call: Call<AboveTopRated>,
                response: Response<AboveTopRated>
            ) {
                this@ListMovieViewModel.listData = response.body()?.results ?: return
                listData.forEach {
                   // Log.v("mojkum", it.original_title)
                }
                //topMovieAdapter.addMovies(lista)
            }

            override fun onFailure(call: Call<AboveTopRated>, t: Throwable) {
                Log.d("mojkum", "onFailure ${t.localizedMessage}")
            }
        })
        return listData
    }
}

