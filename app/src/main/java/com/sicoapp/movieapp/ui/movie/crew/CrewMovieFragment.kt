package com.sicoapp.movieapp.ui.movie.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sicoapp.movieapp.data.repository.RemoteRepository
import com.sicoapp.movieapp.databinding.FragmentMovieCrewBinding
import com.sicoapp.movieapp.ui.movie.crew.adapter.CrewAdapter
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.factory.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CrewMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieCrewBinding

    @Inject
    lateinit var repository: RemoteRepository

    private lateinit var viewModel: CrewViewModel

    private lateinit var viewModelFactory: ViewModelFactory

    val adapter = CrewAdapter()

    var crewId = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.getLong(CREW_ID, -1)?.let {
            crewId = it.toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModelFactory = ViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CrewViewModel::class.java)

        binding = FragmentMovieCrewBinding.inflate(inflater)

        updateUI(crewId)

        binding.data = this

        return binding.root
    }

    private fun updateUI(crewID :Int){
        viewModel.rxToLiveData(crewID).observe(viewLifecycleOwner, {
            val list= it.credits.cast
                    .filter { !it.profilePath.isNullOrBlank() }
                    .distinctBy { it.profilePath }
                    .map { CrewObservable(it) }
            adapter.addCast(list)
        })
    }

}