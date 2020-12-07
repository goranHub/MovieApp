package com.sicoapp.movieapp.ui.fightClub

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.fightClub.MoviesResponse
import com.sicoapp.movieapp.ui.fightClub.adapter.FightClubAdapter
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/6/2020
 */
class FightClubViewModel() : ViewModel() {

    val fightClubAdapter = FightClubAdapter()

    val fightClubApiService =ApiClient().getClient()!!.create(MovieApiService::class.java)
    val callFightClub = fightClubApiService.getAllMyMovies(550, API_KEY)

    init {
        callFightClub.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val lista = response.body() ?: return
                fightClubAdapter.addMovies(lista)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d("error", "onFailure ${t.localizedMessage}")
            }
        })
    }
}

