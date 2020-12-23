package com.sicoapp.movieapp.ui.movie.crew

import android.util.Log
import androidx.databinding.BaseObservable
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.api.retrofitCallCrew
import com.sicoapp.movieapp.data.response.Crew
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewMovieAdpater

class CrewViewModel(
    crewId: Int,
    private val service: MovieApiService?
) : BaseObservable() {
    val adapter = CrewMovieAdpater()
    lateinit var crewList: List<Crew>

    init {
        loadCrew(crewId)
    }

    private fun loadCrew(crewId: Int) {

        retrofitCallCrew(service, crewId,
            {
                val list = it
                    .filter { !it.profilePath.isNullOrBlank() }
                    .distinctBy { it.profilePath }
                    .map { CrewObservable(it) }
                adapter.addCrew(list)
            },{
                Log.d(it, "onFailure if (response.isSuccessful) in MovieApiService ")
            }
        )
    }
}
