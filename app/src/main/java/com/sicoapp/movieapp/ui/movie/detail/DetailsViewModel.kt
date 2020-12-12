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

    @get:Bindable
    var imageUrl by Delegates.observable("TEST imageUrl") { _, _, _ -> notifyPropertyChanged(BR.imageUrl) }

    @get:Bindable
    var overview by Delegates.observable("TEST overview") { _, _, _ -> notifyPropertyChanged(BR.overview) }

    @get:Bindable
    var popularity by Delegates.observable("TEST popularity") { _, _, _ -> notifyPropertyChanged(BR.popularity) }

    @get:Bindable
    var releaseDate by Delegates.observable("TEST releaseDate") { _, _, _ -> notifyPropertyChanged(BR.releaseDate) }

    private val movieDetailsApiServis = ApiClient().getClient()?.create(MovieApiService::class.java)
    private val currentCall = movieDetailsApiServis?.getAllMyMovies(itemId, API_KEY)
    lateinit var responseMovie: Movie

    init {
        currentCall?.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                responseMovie = response.body() ?: return
                imageUrl = "https://image.tmdb.org/t/p/w185/" + responseMovie.posterPath
                overview = responseMovie.overview
                popularity = responseMovie.popularity
                releaseDate = responseMovie.releaseDate

            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error", "onFailure ${t.localizedMessage}")
            }
        })
    }
}

