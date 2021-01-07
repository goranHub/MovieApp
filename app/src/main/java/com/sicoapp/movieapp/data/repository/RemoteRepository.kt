package com.sicoapp.movieapp.data.repository

import com.sicoapp.movieapp.data.api.ApiServis
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.model.response.MovieResponse
import com.sicoapp.movieapp.utils.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

/**
 * @author ll4
 * @date 1/7/2021
 */
class RemoteRepository @Inject constructor(
    private val api : ApiServis,
) {

    suspend fun fetchTopMovies(pageId :Int) : Flow<Result<MovieResponse>> {
        return flow {

            emit(Result.success(api.loadTopRated(API_KEY, pageId.toString())))
        }
    }
}