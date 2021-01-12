package com.sicoapp.movieapp.ui.movie.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding
import com.sicoapp.movieapp.ui.movie.search.BindMulti
import com.sicoapp.movieapp.ui.movie.search.SearchCallback

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var searchItems = mutableListOf<BindMulti>()

    lateinit var mediaTyp: String

    lateinit var callback: SearchCallback

    fun updateItems(newList: List<BindMulti>) {
        searchItems.clear()
        searchItems.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieSearchBinding.inflate(
            layoutInflater, parent, false
        )

        binding.cardItemLayout.setOnClickListener {

            mediaTyp = binding.data?.movie?.media_type.toString()

            binding.data?.movie?.id?.let {
                callback.openDetails(it, mediaTyp)
            }
        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = searchItems[position]
        holder.bind(dataModel)
    }

    override fun getItemCount() = searchItems.size
}




