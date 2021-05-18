package com.sicoapp.movieapp.ui.topmovie.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.databinding.ItemMovieTopBinding

class ViewHolder(val binding: ItemMovieTopBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(obj: Any?) {
        binding.setVariable(BR.data, obj)
        binding.executePendingBindings()
    }
}
