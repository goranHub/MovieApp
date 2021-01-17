package com.sicoapp.movieapp.ui.movie.saved

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.database.SmileyRatingTableModel
import com.sicoapp.movieapp.data.model.response.movie.Movie
import com.sicoapp.movieapp.utils.URL_IMAGE

class SavedAdapter : RecyclerView.Adapter<SavedAdapter.ViewHolderSaved>() {

    var list = mutableListOf<Movie>()

    lateinit var valuesList: List<SmileyRatingTableModel>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSaved {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_saved, parent, false)
        return ViewHolderSaved(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolderSaved, position: Int) {

        val item = list[position]

      /*  val item = valuesList[position]*/

      /*  holder.contentView.text = item.rating.toString()*/

        Glide.with(holder.idView.rootView.context)
            .load(URL_IMAGE + item.posterPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(holder.idView)

    }

    fun addMovies(listItems: Movie) {
        list.add(listItems)
        notifyDataSetChanged()
    }
    fun addRating(values: List<SmileyRatingTableModel>) {
        valuesList = values
        notifyDataSetChanged()
    }

    class ViewHolderSaved(view: View) : RecyclerView.ViewHolder(view) {
        var idView: ImageView = view.findViewById(R.id.item_image)
        val contentView: TextView = view.findViewById(R.id.content)
    }
}