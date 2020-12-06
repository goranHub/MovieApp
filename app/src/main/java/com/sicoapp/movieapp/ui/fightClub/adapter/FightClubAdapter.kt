package com.sicoapp.movieapp.ui.fightClub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.response.fightClub.MoviesResponse
import com.sicoapp.movieapp.utils.CallbackFragmentViewModelAdapter

class FightClubAdapter(val callbackViewModelAdapter: CallbackFragmentViewModelAdapter) :

    RecyclerView.Adapter<FightClubViewHolder>() {


    var list = mutableListOf<MoviesResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FightClubViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return FightClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: FightClubViewHolder, position: Int) {
        val movie = list[position]

        holder.view.findViewById<TextView>(R.id.original_title).text = movie.poster_path
        holder.view.findViewById<TextView>(R.id.overview).text = movie.overview

        holder.view.setOnClickListener {
            callbackViewModelAdapter.onItemClicked(movie.id)
        }
    }

    override fun getItemCount() = list.size

    fun addMovies(moviesResponse: MoviesResponse) {
        list.clear()
        list.addAll(listOf(moviesResponse))
        notifyDataSetChanged()
    }
}
