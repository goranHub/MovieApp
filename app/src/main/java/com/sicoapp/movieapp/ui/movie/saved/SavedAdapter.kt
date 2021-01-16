package com.sicoapp.movieapp.ui.movie.saved

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.api.ApiServisFlow
import com.sicoapp.movieapp.data.database.SmileyRatingTableModel
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.utils.API_KEY
import com.sicoapp.movieapp.utils.URL_IMAGE
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SavedAdapter(
    private val values: List<SmileyRatingTableModel>,
    var api: ApiServisFlow
) : RecyclerView.Adapter<SavedAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = values[position]
        holder.contentView.text = item.rating.toString()

        val singleMovie = item.itemId?.let { api.getByMovieID(it.toLong(), API_KEY) }

        singleMovie
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                object : SingleObserver<Movie> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(response: Movie) {
                        Glide.with(holder.idView.rootView.context)
                            .load(URL_IMAGE + response.posterPath)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(holder.idView)
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", "${e.stackTrace}")
                    }
                }
            )
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var idView: ImageView = view.findViewById(R.id.item_image)
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}