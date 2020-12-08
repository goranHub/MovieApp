package com.sicoapp.movieapp.ui.movie.detail

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.topRated.Movie
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

/**
 * @author ll4
 * @date 12/6/2020
 */
class DetailsViewModel(itemId: Int) : BaseObservable() {

    //kod lambde ako ti parametri ne trbaju stavi se doljna crta
    @get:Bindable
    var imageUrl by Delegates.observable("") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }


    val fightClubApiService = ApiClient().getClient()?.create(MovieApiService::class.java)
    val currentCall = fightClubApiService?.getAllMyMovies(itemId, API_KEY)

    lateinit var responseMovie: Movie

    init {
        currentCall?.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                responseMovie = response.body() ?: return
                imageUrl = "https://image.tmdb.org/t/p/w185/" + responseMovie.poster_path
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error", "onFailure ${t.localizedMessage}")
            }
        })
    }
}

