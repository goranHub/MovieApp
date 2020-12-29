package com.sicoapp.movieapp.data.api

import android.util.Log
import com.sicoapp.movieapp.data.response.*
import com.sicoapp.movieapp.ui.movie.list.ListItemViewModel
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.DetailsObserver
import com.sicoapp.movieapp.utils.Injection
import com.sicoapp.movieapp.utils.URL_IMAGE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/24/2020
 */
private val TAG: String = MovieApiService::class.java.name
val service = Injection.provideMovieApiService().getClient()


fun retrofitCallCrew(
    crewId: Int,
    onSuccess: (crewList: List<Cast>) -> Unit,
    onError: (error: String) -> Unit
)
{
    val currentCall = service.loadCrewById(crewId, API_KEY)

    currentCall.enqueue(object : Callback<Movie> {
        override fun onResponse(
            call: Call<Movie>,
            response: Response<Movie>
        )
        {
            Log.d("movieApiService", "got a response $response")
            if (response.isSuccessful) {
                val crewList = response.body()?.credits?.cast ?: emptyList()
                onSuccess(crewList)
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }
        override fun onFailure(call: Call<Movie>, t: Throwable) {
            Log.d(TAG, "onFailure ${t.localizedMessage}")
        }
    })
}

fun retrofitCallList(
    pageId: Int,
    onSuccess: (movies: List<ListItemViewModel>) -> Unit,
    onError: (error: String) -> Boolean
)
{
    val currentCall = service.loadTopRated(API_KEY, pageId.toString())

    currentCall.enqueue(object : Callback<AboveTopRated>
    {
        override fun onResponse(
            call: Call<AboveTopRated>,
            response: Response<AboveTopRated>
        )
        {
            Log.d("movieApiService", "got a response $response")
            if (response.isSuccessful)
            {
                val moviesList = response.body()?.results ?: emptyList()
                val movieItemsList = moviesList.map { ListItemViewModel(it) }
                onSuccess(movieItemsList)
            }
            else
            {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }
        override fun onFailure(call: Call<AboveTopRated>, t: Throwable)
        {
            Log.d(TAG, "onFailure ${t.localizedMessage}")
        }
    }
    )
}

/*
without recyclerview
 */
fun retrofitCallDetail(itemId: Int,detailsObserver: DetailsObserver)
{
    val currentCall = service.loadById(itemId, API_KEY)
    lateinit var responseMovie: Movie

    currentCall.enqueue(object : Callback<Movie>
    {
        override fun onResponse(
            call: Call<Movie>,
            response: Response<Movie>
        )
        {
            if (response.isSuccessful)
            {
                responseMovie = response.body() ?: return
                detailsObserver.imageUrl = URL_IMAGE + responseMovie.posterPath
                detailsObserver.overview = responseMovie.overview
                detailsObserver.popularity = responseMovie.popularity
                detailsObserver.releaseDate = responseMovie.releaseDate
            }
            else
            {
                val errorResponse = response.errorBody()?.string()
                Log.d(TAG, "$errorResponse")
            }
        }
        override fun onFailure(call: Call<Movie>, t: Throwable)
        {
            Log.d(TAG, "onFailure ${t.localizedMessage}")
        }
    }
    )
}