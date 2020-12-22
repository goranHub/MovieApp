package com.sicoapp.movieapp.ui.movie.crew.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ItemCrewBinding
import com.sicoapp.movieapp.ui.movie.crew.CrewObservable

/**
 * @author ll4
 * @date 12/11/2020
 */
class CrewMovieAdpater() :
    RecyclerView.Adapter<ViewHolder>(){

    var list = mutableListOf<CrewObservable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding :ItemCrewBinding = DataBindingUtil.inflate(
           LayoutInflater.from(parent.context),
           R.layout.item_crew,
           parent,
           false
       )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount() = list.size

    fun addCrew(crewItems: List<CrewObservable>) {
        list.clear()
        list.addAll(crewItems)
        notifyDataSetChanged()
    }
}