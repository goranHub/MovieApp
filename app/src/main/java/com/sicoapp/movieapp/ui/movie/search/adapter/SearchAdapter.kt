package com.sicoapp.movieapp.ui.movie.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding
import com.sicoapp.movieapp.utils.BindMovie

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchAdapter(private val postID: (Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private val TAG = this.javaClass.simpleName

    private var searchItems = ArrayList<BindMovie>()

    fun updateItems(newList: List<BindMovie>) {
        searchItems.clear()
        searchItems.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieSearchBinding.inflate(
            layoutInflater, parent, false
        )

        //holder.itemRowBinding.itemClickListener = clickListener

        binding.cardItemLayout.setOnClickListener{
            binding.data?.movie?.id?.let { it1 -> postID(it1) }
        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = searchItems[position]
        holder.bind(dataModel)
    }

    override fun getItemCount() = searchItems.size

}




