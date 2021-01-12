package com.sicoapp.movieapp.ui.movie.topmovie.adapter

/**
 * @author ll4
 * @date 12/10/2020
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ItemMovieBinding
import com.sicoapp.movieapp.ui.movie.popular.BindMovie
import com.sicoapp.movieapp.ui.movie.topmovie.TopMovieCallback

class TopMovieAdapter : RecyclerView.Adapter<ViewHolder>() {

    lateinit var callback: TopMovieCallback

    var list = mutableListOf<BindMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = list[position]
        holder.bind(dataModel)


        //bind listener with layout
        holder.itemMovieBinding.itemClickListener = object : ItemClickListener {
            override fun openItem(itemId: Long) {
                itemId.let {
                    callback.openDetails(it)
                }
            }
        }

        holder.itemMovieBinding.itemCrewClickListener = object : ItemClickListener {
            override fun openItem(itemId: Long) {
                itemId.let { callback.openCrew(it) }
            }
        }
    }

    override fun getItemCount() = list.size

    fun addMovies(listItems: List<BindMovie>) {
        list.addAll(listItems)
        notifyDataSetChanged()
    }
}