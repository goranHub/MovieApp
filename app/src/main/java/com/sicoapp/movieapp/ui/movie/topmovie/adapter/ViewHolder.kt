package com.sicoapp.movieapp.ui.movie.topmovie.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.databinding.ItemMovieBinding

class ViewHolder(val itemMovieBinding: ItemMovieBinding) :
    RecyclerView.ViewHolder(itemMovieBinding.root) {
    fun bind(obj: Any?) {
        itemMovieBinding.setVariable(BR.data, obj)
        itemMovieBinding.executePendingBindings()
    }
}