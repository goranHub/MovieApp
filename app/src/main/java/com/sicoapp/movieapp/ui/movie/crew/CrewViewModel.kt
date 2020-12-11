package com.sicoapp.movieapp.ui.movie.crew

import android.util.Log
import androidx.databinding.BaseObservable
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.topRated.Movie
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewMovieAdpater
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrewViewModel(crewId: Int) : BaseObservable() {

    val adapter = CrewMovieAdpater()
    private val movieDetailsApiServis = ApiClient().getClient()?.create(MovieApiService::class.java)
    private val currentCall = movieDetailsApiServis?.getCrew(crewId, API_KEY)

    init {
        loadCrew()
    }

    private fun loadCrew() {
         currentCall?.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                val creditsList = response.body()?.credits?.crew ?: return
                val crewItemViewModel = creditsList.map {
                    CrewItemViewModel(it)
                }
                //adapteru dajemo viewmodel
                adapter.addCrew(crewItemViewModel)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error5", "onFailure ${t.localizedMessage}")
            }
        })
    }
}
