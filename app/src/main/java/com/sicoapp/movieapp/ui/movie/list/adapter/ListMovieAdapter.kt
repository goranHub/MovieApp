package com.sicoapp.movieapp.ui.movie.list.adapter

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
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewClickListener
import com.sicoapp.movieapp.ui.movie.list.ListItemViewModel

class ListMovieAdapter(private val postID: (Int) -> Unit, private val crewID: (Int) -> Unit) :
    RecyclerView.Adapter<ViewHolder>(){

    var list = mutableListOf<ListItemViewModel>()

    private val clickListener = object : CustomClickListener {

        // movie id predajemo postId lambdi
        override fun cardClicked(id: Int?) {
            id?.let { postID(it) }
        }
    }

    private val crewListener = object : CrewClickListener {
        override fun crewClicked(id: Int?) {
            id?.let { crewID(it) }
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
        holder.itemRowBinding.itemCrewClickListener = crewListener
    }

    override fun getItemCount() = list.size

    fun addMovies(listItems: List<ListItemViewModel>) {
        list.clear()
        list.addAll(listItems)
        notifyDataSetChanged()
    }
}