package com.sicoapp.movieapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.hsalf.smileyrating.SmileyRating
import com.sicoapp.movieapp.data.database.MovieDatabase
import com.sicoapp.movieapp.data.model.MovieRatingTabelModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * @author ll4
 * @date 12/15/2020
 */
class MovieRepository {

    companion object{
        var movieDatabase : MovieDatabase ? = null
        var movieRatingTabelModel : LiveData<MovieRatingTabelModel>? = null


        fun initDB (context: Context) : MovieDatabase{
            return MovieDatabase.getDataClient(context)
        }

        fun insertData(context: Context, itemId: Int, rating: Int) {

            movieDatabase = initDB(context)

            Log.i("instancaee", movieDatabase.toString())

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
    }

}