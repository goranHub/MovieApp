package com.sicoapp.movieapp.repository

import com.sicoapp.movieapp.data.api.ApiServisFlow
import com.sicoapp.movieapp.data.model.response.movie.MovieResponse
import com.sicoapp.movieapp.utils.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/7/2021
 */
class RemoteRepository @Inject constructor(
    private val api : ApiServisFlow,
) {

    suspend fun fetchTopMovies(pageId :Int) : Flow<Result<MovieResponse>> {
        return flow {
            emit(Result.success(api.loadTopRated(API_KEY, pageId.toString())))
        }
    }

    suspend fun fetchPopular(pageId :Int) : Flow<Result<MovieResponse>> {
        return flow {
            emit(Result.success(api.loadPopular(API_KEY, pageId.toString())))
        }
    }


}