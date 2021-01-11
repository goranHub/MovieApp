package com.sicoapp.movieapp.data.repository

import com.sicoapp.movieapp.data.api.ApiServiceFlowable
import com.sicoapp.movieapp.data.api.ApiServisFlow
import com.sicoapp.movieapp.data.model.response.movie.MovieResponse
import com.sicoapp.movieapp.data.model.response.multi.Multi
import com.sicoapp.movieapp.utils.API_KEY
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/7/2021
 */
class RemoteRepository @Inject constructor(
    private val api : ApiServisFlow,
    val apis : ApiServiceFlowable,
) {

    fun fetchTopMovies(pageId :Int) : Single<MovieResponse> {
        return api.loadTopRated(API_KEY, pageId.toString())
    }

    fun fetchPopularMovies(pageId :Int) : Single<MovieResponse> {
        return api.loadPopular(API_KEY, pageId.toString())
        }


    fun fetchSearchMulti(query:String, pageId :Int) : Single<Multi> {
        return api.searchMulti(API_KEY, query, pageId)
        }


}
