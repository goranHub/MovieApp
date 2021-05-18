package com.sicoapp.movieapp.ui.topmovie.adapter

/**
 * @author ll4
 * @date 12/10/2020
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ItemMovieTopBinding
import com.sicoapp.movieapp.ui.popular.BindMovie


class TopMovieAdapter : RecyclerView.Adapter<ViewHolder>() {

    var list = mutableListOf<BindMovie>()
    var binding: ItemMovieTopBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie_top,
            parent,
            false
        )
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = list[position]
        holder.bind(dataModel)
    }


    override fun getItemCount() = list.size

    fun addMovies(listItems: List<BindMovie>) {
        list.addAll(listItems)
        notifyDataSetChanged()
    }
}