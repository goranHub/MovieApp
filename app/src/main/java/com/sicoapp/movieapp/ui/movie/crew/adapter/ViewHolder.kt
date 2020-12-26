package com.sicoapp.movieapp.ui.movie.crew.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.BR
import com.sicoapp.movieapp.databinding.ItemCreditsBinding

class ViewHolder(val itemRowBinding: ItemCreditsBinding) :
    RecyclerView.ViewHolder(itemRowBinding.root) {
    fun bind(obj: Any?) {
        itemRowBinding.setVariable(BR.data, obj)
        itemRowBinding.executePendingBindings()
    }
}