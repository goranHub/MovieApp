package com.sicoapp.movieapp.ui.movie.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding
import com.sicoapp.movieapp.utils.BindMulti

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchAdapter(private val postIdAndTyp: (Int, String) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    private val TAG = this.javaClass.simpleName

    private var searchItems = ArrayList<BindMulti>()

    lateinit var mediaTyp: String

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

        //holder.itemRowBinding.itemClickListener = clickListener

        binding.cardItemLayout.setOnClickListener{
            mediaTyp = binding.data?.movie?.media_type.toString()
            binding.data?.movie?.id?.let { it1 -> postIdAndTyp(it1, mediaTyp) }
        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = searchItems[position]
        holder.bind(dataModel)
    }

    override fun getItemCount() = searchItems.size

}




