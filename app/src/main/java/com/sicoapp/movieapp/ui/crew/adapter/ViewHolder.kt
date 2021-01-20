package com.sicoapp.movieapp.ui.crew.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.databinding.ItemCreditsBinding

class ViewHolder(val binding: ItemCreditsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(obj: Any?) {
        binding.setVariable(BR.data, obj)
        binding.executePendingBindings()
    }
}