package com.sicoapp.movieapp.data.api

import android.util.Log
import com.sicoapp.movieapp.data.response.*
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.BindMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/24/2020
 */

private val TAG: String = MovieApiService::class.java.name
val service = MovieApiService.getClient()


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





