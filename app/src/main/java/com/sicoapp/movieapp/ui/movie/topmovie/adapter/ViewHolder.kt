package com.sicoapp.movieapp.ui.movie.topmovie.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.databinding.ItemMovieBinding

class ViewHolder(val itemRowBinding: ItemMovieBinding) :
    RecyclerView.ViewHolder(itemRowBinding.root) {
    fun bind(obj: Any?) {
        itemRowBinding.setVariable(BR.data, obj)
        itemRowBinding.executePendingBindings()
    }
}