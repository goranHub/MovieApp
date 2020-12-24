package com.sicoapp.movieapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.sicoapp.movieapp.data.database.MovieDatabaseForSmiley
import com.sicoapp.movieapp.data.model.MovieRatingTabelModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * @author ll4
 * @date 12/15/2020
 */
class MovieRepository() {

    companion object{
        var movieDatabase : MovieDatabaseForSmiley ? = null
        var movieRatingTabelModel : LiveData<MovieRatingTabelModel>? = null

        fun initDB (context: Context) : MovieDatabaseForSmiley{
            return MovieDatabaseForSmiley.getDataClient(context)
        }

        fun insertData(context: Context, itemId: Int, rating: Int) {

            movieDatabase = initDB(context)

            CoroutineScope(IO).launch {
                val movieRatingDetails = MovieRatingTabelModel(itemId, rating)
                movieDatabase!!.movieDao().InsertData(movieRatingDetails)
            }
        }

        fun getMovieRatingDetails(context: Context, itemId: Int) : LiveData<MovieRatingTabelModel> {
            movieDatabase = initDB(context)
            movieRatingTabelModel = movieDatabase!!.movieDao().getMovieDetails(itemId)
            return movieRatingTabelModel as LiveData<MovieRatingTabelModel>
        }

        fun removeDataForThatItem(context: Context, itemId: Int) {
            movieDatabase = initDB(context)
            CoroutineScope(IO).launch {
                movieDatabase!!.movieDao().removeDataForThatItem(itemId)
            }
        }
    }
}