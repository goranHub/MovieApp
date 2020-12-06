package com.sicoapp.movieapp.ui.topMovies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.topRated.AboveTopRated
import com.sicoapp.movieapp.ui.topMovies.adapter.TopMovieAdapter
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/6/2020
 */
class TopMoviesViewModel : ViewModel() {

    fun retrofitCall(recyclerView: RecyclerView) {

        val topMoviesApiService = ApiClient().getClient()?.create(MovieApiService::class.java) ?: return
        val callAllTopMovies = topMoviesApiService.getTopRatedMovies(API_KEY)

        val topMovieAdapter = TopMovieAdapter()
        recyclerView.adapter = topMovieAdapter

        callAllTopMovies.enqueue(object : Callback<AboveTopRated> {
            override fun onResponse(
                call: Call<AboveTopRated>,
                response: Response<AboveTopRated>
            ) {
                val lista = response.body()?.results ?: return

                lista.forEach {
                    Log.v("mojkum", it.original_title)
                }
                topMovieAdapter.addMovies(lista)
            }
            override fun onFailure(call: Call<AboveTopRated>, t: Throwable) {
                Log.d("mojkum", "onFailure ${t.localizedMessage}")
            }
        })
    }
}