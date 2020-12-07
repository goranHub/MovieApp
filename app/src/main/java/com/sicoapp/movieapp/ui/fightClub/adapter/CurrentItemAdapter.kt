package com.sicoapp.movieapp.ui.fightClub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.response.current.MoviesResponse

class CurrentItemAdapter() :
    RecyclerView.Adapter<CurrentItemViewHolder>() {

    var list = mutableListOf<MoviesResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return CurrentItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrentItemViewHolder, position: Int) {
        val movie = list[position]
        holder.view.findViewById<TextView>(R.id.original_title).text = movie.poster_path
        holder.view.findViewById<TextView>(R.id.overview).text = movie.overview

    }

    override fun getItemCount() = list.size

    fun addMovies(moviesResponse: MoviesResponse) {
        list.clear()
        list.addAll(listOf(moviesResponse))
        notifyDataSetChanged()
    }
}
