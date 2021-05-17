package com.sicoapp.movieapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding
import com.sicoapp.movieapp.ui.search.BindMulti

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchAdapter : RecyclerView.Adapter<ViewHolder>() {

    private var searchItems = mutableListOf<BindMulti>()
    private var onClickListener: OnClickListener? = null
    lateinit var binding : ItemMovieSearchBinding
    lateinit var mediaTyp: String

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
                onClickListener?.openDetails(movieId, mediaTyp)
            }
        }
    }

    override fun getItemCount() = searchItems.size

    fun updateItems(newList: List<BindMulti>) {
        searchItems.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        searchItems.clear()
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun openDetails(movieId: Long, mediaTyp: String)
    }
}




