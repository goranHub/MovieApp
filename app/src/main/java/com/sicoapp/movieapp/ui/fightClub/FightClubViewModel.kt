package com.sicoapp.movieapp.ui.fightClub

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.fightClub.MoviesResponse
import com.sicoapp.movieapp.ui.fightClub.adapter.FightClubAdapter
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.CallbackFragmentViewModelAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author ll4
 * @date 12/6/2020
 */
class FightClubViewModel(fightCallback: CallbackFragmentViewModelAdapter) : ViewModel() {

    private val callbackViewModelAdapter = object : CallbackFragmentViewModelAdapter {
        override fun onItemClicked(postID: Int) {
            fightCallback.navigateToNextScren(postID)
        }
        override fun navigateToNextScren(postID: Int) {
        }
    }

    val fightClubAdapter = FightClubAdapter(callbackViewModelAdapter)
    val fightClubApiService =ApiClient().getClient()!!.create(MovieApiService::class.java)
    val callFightClub = fightClubApiService.getAllMyMovies(550, API_KEY)

    init {

        //set in xml
        //recyclerView.adapter = fightClubAdapter

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

