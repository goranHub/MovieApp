package com.sicoapp.movieapp.ui.movie.saved

import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sicoapp.movieapp.data.database.SmileyRatingTableModel
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.data.model.response.movie.MovieResponse
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.ui.movie.topmovie.adapter.TopMovieAdapter
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.URL_IMAGE
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.properties.Delegates

/**
 * @author ll4
 * @date 12/6/2020
 */
class SavedViewModel @ViewModelInject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    val adapter = SavedAdapter()

     fun loadRemoteData( values: List<SmileyRatingTableModel>) {

         var vale by Delegates.notNull<Long>()

         values.map {
               vale= it.itemId!!.toLong()
         }

         val singleMovie =   remoteRepository.api.getByMovieID(vale, API_KEY)


         singleMovie
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                 object : SingleObserver<Movie> {
                     override fun onSubscribe(d: Disposable) {
                     }

                     override fun onSuccess(response: Movie) {
                        // adapter.addRating(values)
                         adapter.addMovies(response)
                     }

                     override fun onError(e: Throwable) {
                         Log.d("error", "${e.stackTrace}")
                     }
                 }
             )
    }
}


