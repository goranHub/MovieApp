package com.sicoapp.movieapp.ui.movie.detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.model.MovieRatingTabelModel
import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.repository.MovieRepository
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.URL_IMAGE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @author ll4
 * @date 12/6/2020
 */
class DetailsViewModel(itemId: Int) : ViewModel() {

    var detailsObserver = DetailsObserver()

    var liveDataMovieRating: LiveData<MovieRatingTabelModel>? = null

    init {
        loadDetailsMovies(itemId)
    }

    private fun loadDetailsMovies(itemId: Int) {
        val movieDetailsApiServis =
            ApiClient().getClient()?.create(MovieApiService::class.java) ?: return
        val currentCall = movieDetailsApiServis.getAllMyMoviesById(itemId, API_KEY)
        lateinit var responseMovie: Movie

        currentCall.enqueue(object : Callback<Movie> {
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                responseMovie = response.body() ?: return
                detailsObserver.imageUrl = URL_IMAGE + responseMovie.posterPath
                detailsObserver.overview = responseMovie.overview
                detailsObserver.popularity = responseMovie.popularity
                detailsObserver.releaseDate = responseMovie.releaseDate
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error", "onFailure ${t.localizedMessage}")
            }
        })
    }

    fun insertData(context: Context, itemID: Int, rating: Int) {
        MovieRepository.insertData(context, itemID, rating)
    }

    fun getMovieRatingDetails(
        context: Context,
        itemId: Int
    ): LiveData<MovieRatingTabelModel>? {
        liveDataMovieRating = MovieRepository.getMovieRatingDetails(context, itemId)
        return liveDataMovieRating
    }

    fun removeDataForThatItem(context: Context, itemId: Int) {
        MovieRepository.removeDataForThatItem(context, itemId)
    }
}

