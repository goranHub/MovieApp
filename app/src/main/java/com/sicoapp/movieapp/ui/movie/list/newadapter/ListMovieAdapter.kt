package com.sicoapp.movieapp.ui.movie.list.newadapter

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

class ListMovieAdapter(private val postID: (Int) -> Unit) :
    RecyclerView.Adapter<ViewHolder>(){

    var list = mutableListOf<MovieItemViewModel>()

    private val clickListener = object : CustomClickListener {

        // movie id predajemo postId lambdi
        override fun cardClicked(id: Int?) {
            id?.let { postID(it) }
        }
    }

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
        holder.itemRowBinding.itemClickListener = clickListener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addMovies(movieItems: List<MovieItemViewModel>) {
        list.clear()
        list.addAll(movieItems)
        notifyDataSetChanged()
    }
}