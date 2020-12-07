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
    lateinit var responseTopRated: TopRated

    init {
        currentCall.enqueue(object : Callback<TopRated> {
            override fun onResponse(
                call: Call<TopRated>,
                response: Response<TopRated>
            ) {


                responseTopRated = response.body() ?: return



                responseTopRated = TopRated(
                    adult = responseTopRated.adult,
                    backdrop_path = responseTopRated.original_language,
                    belongs_to_collection = responseTopRated.belongs_to_collection,
                    budget = responseTopRated.budget,
                    genres = responseTopRated.genres,
                    homepage = responseTopRated.homepage,
                    id = responseTopRated.id,
                    imdb_id = responseTopRated.imdb_id,
                    original_language = responseTopRated.original_language,
                    original_title = responseTopRated.original_title,
                    overview = responseTopRated.overview,
                    popularity = responseTopRated.popularity,
                    poster_path = responseTopRated.poster_path,
                    production_companies = responseTopRated.production_companies
                )
                callback.listToFragment(responseTopRated)
            }

            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                Log.d("error", "onFailure ${t.localizedMessage}")
            }
        })
    }
}

