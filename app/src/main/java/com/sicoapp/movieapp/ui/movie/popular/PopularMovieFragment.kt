package com.sicoapp.movieapp.ui.movie.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.data.firebase.FireStoreClass
import com.sicoapp.movieapp.databinding.FragmentMoviePopularBinding
import com.sicoapp.movieapp.ui.movie.BaseFragment
import com.sicoapp.movieapp.ui.movie.login.EntryActivity
import com.sicoapp.movieapp.ui.movie.topmovie.TopMovieCallback
import com.sicoapp.movieapp.utils.CREW_ID
import com.sicoapp.movieapp.utils.ITEM_ID
import com.sicoapp.movieapp.utils.USER_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_entry.*

/**
 * @author ll4
 * @date 1/1/2021
 */
@AndroidEntryPoint
class PopularMovieFragment : BaseFragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentMoviePopularBinding

    private val viewModel: PopularViewModel by viewModels()

    val callback = object : TopMovieCallback {
        override fun openDetails(movieId: Long) {
            val bundleItemId = bundleOf(ITEM_ID to movieId)
            findNavController().navigate(
                R.id.action_popularMovieFragment_to_movieDetailsFragment,
                bundleItemId
            )
        }

        override fun openCrew(crewId: Long) {
            val bundleCrewId = bundleOf(CREW_ID to crewId)
            findNavController().navigate(
                R.id.action_popularMovieFragment_to_crewMovieFragment,
                bundleCrewId
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviePopularBinding.inflate(inflater)
        binding.data = viewModel
        binding.callback = callback

        scrollRecyclerView()

        setNavigationViewListener()

        return binding.root
    }

    private fun scrollRecyclerView() {
        binding.recylerViewFragmentTopMovie.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadRemoteData()
                }
            }
        })
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.my_profile -> {
            }

            R.id.list_movie_saved -> {
                findNavController().navigate(
                    R.id.action_popularMovieFragment_to_itemFragment
                )
            }

            R.id.sign_out -> {
                //TODO signout function
                val currentUserID = FireStoreClass().getCurrentUserID()
                val userIdBundle = bundleOf(USER_ID to currentUserID)
                findNavController().navigate(
                    R.id.action_popularMovieFragment_to_introFragment, userIdBundle
                )
            }
        }
        (activity as EntryActivity).drawer_layout?.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setNavigationViewListener() {
        val navigationView = (activity as EntryActivity).navigation_view
        navigationView.setNavigationItemSelectedListener(this)
    }


    override fun onResume() {
        super.onResume()
        (activity as EntryActivity?)!!.supportActionBar?.show()
        (activity as EntryActivity?)!!.bottomNav.visibility = View.VISIBLE

    }

    override fun onStop() {
        super.onStop()
    }
}