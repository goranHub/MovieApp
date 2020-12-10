package com.sicoapp.movieapp.ui.movie.list.newadapter

/**
 * @author ll4
 * @date 12/10/2020
 */

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.response.topRated.Movie
import com.sicoapp.movieapp.databinding.ItemMovieBinding


class MyRecyclerViewAdapter(private val postID: (Int) -> Unit,private val ctx: Context) : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>(), CustomClickListener {

    private var context: Context? = null
    var list = mutableListOf<Movie>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {val binding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel: Movie = list[position]
        holder.bind(dataModel)
        holder.itemRowBinding.itemClickListener = this
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemRowBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemRowBinding.root) {
        var itemRowBinding: ItemMovieBinding = itemRowBinding
        fun bind(obj: Any?) {
            itemRowBinding.setVariable(BR.movie, obj)
            itemRowBinding.executePendingBindings()
        }
    }

    fun addMovies(moviesResponse: List<Movie>){
        list.clear()
        list.addAll(moviesResponse)
        notifyDataSetChanged()
    }

    init {
        context = ctx
    }

    override fun cardClicked(movie: Movie?) {
        movie?.let { postID(it.id) }
    }
}