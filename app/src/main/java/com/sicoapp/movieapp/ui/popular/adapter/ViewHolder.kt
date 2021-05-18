package com.sicoapp.movieapp.ui.popular.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.databinding.ItemMoviePopularBinding

class ViewHolder(val binding: ItemMoviePopularBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(obj: Any?) {
        binding.setVariable(BR.data, obj)
        binding.executePendingBindings()
    }
}
