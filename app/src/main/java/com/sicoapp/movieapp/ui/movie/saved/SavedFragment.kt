package com.sicoapp.movieapp.ui.movie.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sicoapp.movieapp.data.api.ApiServisFlow
import com.sicoapp.movieapp.data.database.SmileyRatingTableModel
import com.sicoapp.movieapp.data.repository.SmileyRepository
import com.sicoapp.movieapp.databinding.FragmentSavedListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class SavedFragment : Fragment() {

    @Inject
    lateinit var smileyRepository: SmileyRepository

    @Inject
    lateinit var api: ApiServisFlow

    private lateinit var binding: FragmentSavedListBinding


    private val viewModel: SavedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSavedListBinding.inflate(inflater)

        binding.listMovieSaved.adapter = viewModel.adapter

        runBlocking {
            viewModel.loadRemoteData(getSaved())
        }

        return binding.root
    }

    private suspend fun getSaved(): List<SmileyRatingTableModel> {
        val saved = smileyRepository.getSaved()
        return saved.distinctBy { it.itemId }
    }
}