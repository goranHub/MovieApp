package com.sicoapp.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.response.fightClub.MoviesResponse
import com.sicoapp.movieapp.data.response.topRated.AboveTopRated
import com.sicoapp.movieapp.data.response.topRated.TopRated

/**
 * @author ll4
 * @date 12/6/2020
 */
class TopMovieAdapter():RecyclerView.Adapter<TopMovieViewHolder>() {

    var list = mutableListOf<TopRated>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return TopMovieViewHolder(view)
    }


    override fun onBindViewHolder(holder: TopMovieViewHolder, position: Int) {
        val movie = list[position]
        holder.view.findViewById<TextView>(R.id.original_title).text = movie.original_title
        holder.view.findViewById<TextView>(R.id.overview).text = movie.overview
    }

    override fun getItemCount() = list.size

    fun addMovies(moviesResponse: List<TopRated>){
        list.clear()
        list.addAll(moviesResponse)
        notifyDataSetChanged()
    }
}