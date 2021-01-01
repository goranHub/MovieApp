package com.sicoapp.movieapp.ui.movie.crew

import android.util.Log
import androidx.databinding.BaseObservable
import com.sicoapp.movieapp.data.api.retrofitCallCrew
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewAdapter

class CrewViewModel(
    crewId: Int,
) : BaseObservable() {

    val adapter = CrewAdapter()

    init {
        loadCrew(crewId)
    }

    private fun loadCrew(crewId: Int) {
        retrofitCallCrew(crewId,
            { it ->
                val list = it
                    .filter { !it.profilePath.isNullOrBlank() }
                    .distinctBy { it.profilePath }
                    .map { CrewObservable(it) }
                adapter.addCast(list)
            },
            {
                Log.d(it, "onFailure if (response.isSuccessful) in MovieApiService ")
            }
        )
    }
}
