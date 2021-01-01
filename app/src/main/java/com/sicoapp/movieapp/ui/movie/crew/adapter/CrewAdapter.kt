package com.sicoapp.movieapp.ui.movie.crew.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ItemCreditsBinding
import com.sicoapp.movieapp.ui.movie.crew.CrewObservable

/**
 * @author ll4
 * @date 12/11/2020
 */
class CrewAdapter: RecyclerView.Adapter<ViewHolder>(){

    var list = mutableListOf<CrewObservable>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding :ItemCreditsBinding = DataBindingUtil.inflate(
           LayoutInflater.from(parent.context),
           R.layout.item_credits,
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

    fun addCast(crewItems: List<CrewObservable>) {
        list.addAll(crewItems)
        notifyDataSetChanged()
    }
}