package com.sicoapp.movieapp.ui.movie.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.databinding.ItemMovieBinding
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

    lateinit var binding : ItemMovieSearchBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemMovieSearchBinding.inflate(
            layoutInflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = searchItems[position]
        holder.bind(dataModel)

        binding.cardItemLayout.setOnClickListener {
            mediaTyp = holder.binding.data?.movie?.media_type.toString()
            holder.binding.data?.movie?.id?.let { movieId->
                callback.openDetails(movieId, mediaTyp)
            }
        }
    }

    override fun getItemCount() = searchItems.size

    fun updateItems(newList: List<BindMulti>) {
        searchItems.addAll(newList)
        notifyDataSetChanged()
    }
}




