package com.sicoapp.movieapp.ui.search.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding

/**
 * @author ll4
 * @date 1/5/2021
 */
class ViewHolder(val binding: ItemMovieSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(obj: Any?) {
        binding.setVariable(BR.data, obj)
        binding.executePendingBindings()
    }
}

