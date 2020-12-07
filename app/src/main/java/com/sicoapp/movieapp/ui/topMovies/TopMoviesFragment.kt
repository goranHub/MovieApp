package com.sicoapp.movieapp.ui.topMovies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.FragmentTopMovieBinding
import com.sicoapp.movieapp.utils.ITEM_ID


class TopMoviesFragment : Fragment() {

    private lateinit var binding : FragmentTopMovieBinding
    private  val viewModel by lazy {  TopMoviesViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(ITEM_ID, -1)?.let {
            Log.d("mojArgumentizFighta", "je $it")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_movie, container, false)
        binding.data = viewModel
        return binding.root
    }
}