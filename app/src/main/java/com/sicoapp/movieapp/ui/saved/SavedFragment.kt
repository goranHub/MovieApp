package com.sicoapp.movieapp.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sicoapp.movieapp.data.remote.MovieServis
import com.sicoapp.movieapp.databinding.FragmentSavedListBinding
import com.sicoapp.movieapp.domain.IRepository
import com.sicoapp.movieapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SavedFragment : BaseFragment() {

    @Inject
    lateinit var repository: IRepository

    @Inject
    lateinit var api: MovieServis

    private lateinit var binding: FragmentSavedListBinding

    private val viewModel: SavedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedListBinding.inflate(inflater)

        binding.listMovieSaved.adapter = viewModel.adapter

        return binding.root
    }
}