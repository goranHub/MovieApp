package com.sicoapp.movieapp.ui.currentItem

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.topRated.TopRated
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.CallbackList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/6/2020
 */
class CurrentItemViewModel(itemId: Int, callback: CallbackList) : ViewModel() {


    val fightClubApiService = ApiClient().getClient()!!.create(MovieApiService::class.java)
    val currentCall = fightClubApiService.getAllMyMovies(itemId, API_KEY)

    init {
        currentCall.enqueue(object : Callback<TopRated> {
            override fun onResponse(
                call: Call<TopRated>,
                response: Response<TopRated>
            ) {
                val lista = response.body() ?: return
                callback.listToFragment(lista)
            }

            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                Log.d("error", "onFailure ${t.localizedMessage}")
            }
        })
    }
}

