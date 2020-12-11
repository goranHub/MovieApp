package com.sicoapp.movieapp.ui.movie.crew

import android.util.Log
import androidx.databinding.BaseObservable
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.topRated.Movie
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrewViewModel(itemId: Int) : BaseObservable(){




    private val movieDetailsApiServis = ApiClient().getClient()?.create(MovieApiService::class.java)
    private val currentCall = movieDetailsApiServis?.getAllMyMovies(itemId, API_KEY)
    lateinit var responseMovie: Movie

    init {
        val enqueue = currentCall?.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                val creditsList = response.body()?.credits?.crew ?: return
                val creditItemList =creditsList.map {
                    CrewItemViewModel(it)
                }

               // adapter.addCrew(creditItemList)

            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error", "onFailure ${t.localizedMessage}")
            }
        })
    }



}
