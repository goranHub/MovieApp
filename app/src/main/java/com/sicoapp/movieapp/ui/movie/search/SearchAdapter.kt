package com.sicoapp.movieapp.ui.movie.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sicoapp.movieapp.databinding.ItemMovieSearchBinding
import com.sicoapp.movieapp.utils.BindMovie
import com.sicoapp.movieapp.utils.URL_IMAGE

/**
 * @author ll4
 * @date 1/4/2021
 */
class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val TAG = this.javaClass.simpleName

    private var searchItems = ArrayList<BindMovie>()

    lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(movie: BindMovie, imageView: ImageView, textView: TextView)
    }


    fun updateItems(newList: List<BindMovie>) {
        if (newList !== null) {
            if (searchItems.isNotEmpty())
                searchItems.removeAt(searchItems.size - 1)
            searchItems.clear()
            searchItems.addAll(newList)
        } else {
            searchItems.add(newList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieSearchBinding.inflate(
            layoutInflater, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = searchItems[position]
        holder.bind(dataModel)
    }

    override fun getItemCount() = searchItems.size


    inner class ViewHolder(val binding: ItemMovieSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(searchItem: BindMovie) {

            binding.poster.transitionName = searchItem.movie.posterPath
            binding.title.transitionName = searchItem.movie.originalTitle

            binding.title.text = searchItem.movie.originalTitle
            binding.relase.text = searchItem.movie.releaseDate

         //   var imageUrl = URL_IMAGE + searchItem.movie.posterPath

            searchItem.movie.posterPath?.let {
                Log.d(TAG, it)

                Glide
                    .with(binding.root)
                    .load(URL_IMAGE +it)
                    .into(binding.poster)
            }

            binding.mcvItemLayout.setOnClickListener {
                   listener.onItemClick(searchItem, binding.poster, binding.title)
            }
        }
    }
}