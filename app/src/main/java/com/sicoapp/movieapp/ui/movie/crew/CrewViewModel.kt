package com.sicoapp.movieapp.ui.movie.crew

import android.util.Log
import androidx.databinding.BaseObservable
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.topRated.Crew
import com.sicoapp.movieapp.data.response.topRated.Movie
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewMovieAdpater
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrewViewModel(crewId: Int) : BaseObservable() {
    val adapter = CrewMovieAdpater()
    lateinit var creditsList: List<Crew>

    init {
        loadCrew(crewId)
    }

    private fun loadCrew(crewId: Int) {

        val movieDetailsApiServis = ApiClient().getClient()?.create(MovieApiService::class.java)
        val currentCall = movieDetailsApiServis?.getCrew(crewId, API_KEY)


        currentCall?.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                creditsList = response.body()?.credits?.crew ?: return

                creditsList= creditsList.distinctBy {
                    it.profile_path
                }

                val list = creditsList
                    .filter {(!it.profile_path.isNullOrBlank()) }
                    .map {CrewItemViewModel(it)}

                adapter.addCrew(list)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error5", "onFailure ${t.localizedMessage}")
            }
        })
    }
}
