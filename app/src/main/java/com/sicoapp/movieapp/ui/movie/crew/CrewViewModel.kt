package com.sicoapp.movieapp.ui.movie.crew

import android.util.Log
import androidx.databinding.BaseObservable
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.api.MovieApiService.Companion.getClient
import com.sicoapp.movieapp.data.response.Crew
import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewMovieAdpater
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrewViewModel(crewId: Int) : BaseObservable() {
    val adapter = CrewMovieAdpater()
    lateinit var crewList: List<Crew>

    init {
        loadCrew(crewId)
    }

    private fun loadCrew(crewId: Int) {

        val movieDetailsApiServis = getClient()?.create(MovieApiService::class.java)
        val currentCall = movieDetailsApiServis?.getCrew(crewId, API_KEY)

        currentCall?.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                crewList = response.body()?.credits?.crew ?: return

                val list = crewList
                    //.asSequence()
                    .filter { !it.profilePath.isNullOrBlank() }
                    .distinctBy { it.profilePath }
                    .map { CrewObservable(it) }
                    //.toMutableList()

                adapter.addCrew(list)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error5", "onFailure ${t.localizedMessage}")
            }
        })
    }
}
