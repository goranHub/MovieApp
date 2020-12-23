package com.sicoapp.movieapp.data.api

import android.util.Log
import com.sicoapp.movieapp.data.response.AboveTopRated
import com.sicoapp.movieapp.data.response.Crew
import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.ui.movie.detail.DetailsObserver
import com.sicoapp.movieapp.ui.movie.list.ListItemViewModel
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.BASE_URL
import com.sicoapp.movieapp.utils.URL_IMAGE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author ll4
 * @date 12/6/2020
 */


fun retrofitCallCrew(
    service: MovieApiService?,
    id: Int,
    onSuccess: (movies: List<Crew>) -> Unit,
    onError: (error: String) -> Unit
) {
    // val movieDetailsApiServis = MovieApiService.getClient()?.create(MovieApiService::class.java)
    val currentCall = service?.getCrew(id, API_KEY)

    currentCall?.enqueue(object : Callback<Movie> {
        override fun onResponse(
            call: Call<Movie>,
            response: Response<Movie>
        ) {
            Log.d("movieApiService", "got a response $response")
            if (response.isSuccessful) {
                val crewList = response.body()?.credits?.crew ?: emptyList()
                onSuccess(crewList)
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }

        override fun onFailure(call: Call<Movie>, t: Throwable) {
            Log.d("error5", "onFailure ${t.localizedMessage}")
        }
    })
}


fun retrofitCallList(
    service: MovieApiService?,
    pageId: Int,
    onSuccess: (movies: List<ListItemViewModel>) -> Unit,
    onError: (error: String) -> Unit
) {
    val currentCall = service?.getTopRatedMovies(API_KEY, pageId.toString())

    currentCall?.enqueue(object : Callback<AboveTopRated> {
        override fun onResponse(
            call: Call<AboveTopRated>,
            response: Response<AboveTopRated>
        ) {
            Log.d("movieApiService", "got a response $response")
            if (response.isSuccessful) {
                val moviesList = response.body()?.results ?: emptyList()
                val movieItemsList = moviesList.map { ListItemViewModel(it) }
                onSuccess(movieItemsList)
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }

        override fun onFailure(call: Call<AboveTopRated>, t: Throwable) {
            Log.d("error5", "onFailure ${t.localizedMessage}")
        }
    })
}

fun retrofitCallDetail(
    service: MovieApiService?,
    itemId: Int,
    detailsObserver : DetailsObserver
) {
    val currentCall = service?.getAllMyMoviesById(itemId, API_KEY)

    lateinit var responseMovie: Movie

    currentCall?.enqueue(object : Callback<Movie> {
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

interface MovieApiService {

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?

    ): Call<AboveTopRated>


    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: String?
    ): Response<AboveTopRated>

    @GET("movie/{id}")
    fun getAllMyMoviesById(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<Movie>

    @GET("movie/{id}?&append_to_response=credits")
    fun getCrew(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<Movie>

    companion object {

        private var retrofit: Retrofit? = null

        fun getClient(): MovieApiService {

            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApiService::class.java)
        }
    }
}