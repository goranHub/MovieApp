package com.sicoapp.movieapp.ui.movie.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sicoapp.movieapp.data.api.ApiServiceFlowable
import com.sicoapp.movieapp.databinding.FragmentMovieCrewBinding
import com.sicoapp.movieapp.utils.CREW_ID
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class CrewMovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieCrewBinding
    var crewId = 0

    @Inject
    lateinit var api: ApiServiceFlowable

    private val viewModel by lazy { CrewViewModel(crewId, api) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(CREW_ID, -1)?.let {
            crewId = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieCrewBinding.inflate(inflater)

        updateUI()

        binding.data = viewModel
        return binding.root
    }

    private fun updateUI(){
        viewModel.rxToLiveData().observe(viewLifecycleOwner, {
            val list= it.credits.cast
                    .filter { !it.profilePath.isNullOrBlank() }
                    .distinctBy { it.profilePath }
                    .map { CrewObservable(it) }
            viewModel.adapter.addCast(list)
        })
    }

}