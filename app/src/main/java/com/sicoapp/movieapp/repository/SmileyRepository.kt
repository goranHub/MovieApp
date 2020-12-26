package com.sicoapp.movieapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.sicoapp.movieapp.data.database.MovieDatabaseForSmiley
import com.sicoapp.movieapp.data.model.MovieRatingTableModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * @author ll4
 * @date 12/15/2020
 */
class SmileyRepository() {

    companion object{
        var movieDatabase : MovieDatabaseForSmiley ? = null
        private var movieRatingTableModel : LiveData<MovieRatingTableModel>? = null

        private fun initDB (context: Context) : MovieDatabaseForSmiley{
            return MovieDatabaseForSmiley.getDataClient(context)
        }

        fun insertData(context: Context, itemId: Int, rating: Int) {

            movieDatabase = initDB(context)

            CoroutineScope(IO).launch {
                val movieRatingDetails = MovieRatingTableModel(itemId, rating)
                movieDatabase!!.movieDao().insert(movieRatingDetails)
            }
        }

        fun getMovieRatingDetails(context: Context, itemId: Int) : LiveData<MovieRatingTableModel> {
            movieDatabase = initDB(context)
            movieRatingTableModel = movieDatabase!!.movieDao().loadById(itemId)
            return movieRatingTableModel as LiveData<MovieRatingTableModel>
        }

        fun removeDataForThatItem(context: Context, itemId: Int) {
            movieDatabase = initDB(context)
            CoroutineScope(IO).launch {
                movieDatabase!!.movieDao().deleteByID(itemId)
            }
        }
    }
}