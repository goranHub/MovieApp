package com.sicoapp.movieapp.ui.movie.crew

import android.util.Log
import androidx.databinding.BaseObservable
import com.sicoapp.movieapp.data.api.retrofitCallCrew
import com.sicoapp.movieapp.data.response.Crew
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewMovieAdpater

class CrewViewModel(
    crewId: Int,
) : BaseObservable() {

    val adapter = CrewMovieAdpater()

    init {
        loadCrew(crewId)
    }

    private fun loadCrew(crewId: Int) {
        retrofitCallCrew(crewId,
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
