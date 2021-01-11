package com.sicoapp.movieapp.ui.movie.crew

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.schedulers.Schedulers

class CrewViewModel (
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    fun rxToLiveData(crewId : Int) : LiveData<Movie> {
        return LiveDataReactiveStreams.fromPublisher(
            remoteRepository.apis.loadCrewBy(crewId, API_KEY,)
                .subscribeOn(Schedulers.io())
        )
    }
}
