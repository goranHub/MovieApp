package com.sicoapp.movieapp.ui.movie.list

/**
 * @author ll4
 * @date 12/8/2020
 */
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.response.topRated.Movie

/*
class MovieAdapter(private val postID : (Int)-> Unit) : RecyclerView.Adapter<MovieAdapter.ListMovieViewHolder>() {

    var list = mutableListOf<Movie>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return ListMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) {
        val movie = list[position]
        holder.view.findViewById<TextView>(R.id.original_title).text = movie.original_title
        holder.view.findViewById<TextView>(R.id.overview).text = movie.overview
        holder.view.setOnClickListener {
            postID(movie.id)
        }
    }

    override fun getItemCount() = list.size

    fun addMovies(moviesResponse: List<Movie>){
        list.clear()
        list.addAll(moviesResponse)
        notifyDataSetChanged()
    }
    class ListMovieViewHolder(val view: View) : RecyclerView.ViewHolder(view){}
}

*/