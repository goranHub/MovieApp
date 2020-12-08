package com.sicoapp.movieapp.ui.movie.list

/**
 * @author ll4
 * @date 12/8/2020
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.data.response.topRated.Movie
import com.sicoapp.movieapp.databinding.ItemMovieBinding
import com.sicoapp.movieapp.utils.CallbackFragmentViewModelAdapter
import kotlinx.android.synthetic.main.item_movie.view.*


class MovieAdapter(private val callback : CallbackFragmentViewModelAdapter, movieList : List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieList = mutableListOf<Movie>()

    class MovieViewHolder(itemView: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemView.imageView) {

        private var binding: ItemMovieBinding? = null


        fun bind(movie: Movie?) {
            binding!!.movie = movie
            binding!!.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieViewHolder {
        val layotInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemMovieBinding.inflate(layotInflater, parent, false)

        val movie = movieList[position]
        parent.imageView.setOnClickListener {
            callback.onItemClicked(movie.id)
        }

        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList.get(position)
        holder.bind(movie)
    }

    override fun getItemCount() = movieList.size
}

