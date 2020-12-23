package com.sicoapp.movieapp.data.api

import android.util.Log
import com.sicoapp.movieapp.data.response.AboveTopRated
import com.sicoapp.movieapp.data.response.Crew
import com.sicoapp.movieapp.data.response.Movie
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.BASE_URL
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
    service: MovieApiService.Companion,
    id: Int,
    onSuccess: (movies: List<Crew>) -> Unit,
    onError: (error: String ) ->  Unit
){
    val movieDetailsApiServis = MovieApiService.getClient()?.create(MovieApiService::class.java)
    val currentCall = movieDetailsApiServis?.getCrew(id, API_KEY)

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

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}