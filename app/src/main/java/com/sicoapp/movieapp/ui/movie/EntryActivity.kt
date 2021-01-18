package com.sicoapp.movieapp.ui.movie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sicoapp.movieapp.R
import com.sicoapp.movieapp.databinding.ActivityEntryBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * @author ll4
 * @date 1/14/2021
 */
@AndroidEntryPoint
class EntryActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var bottomNav: BottomNavigationView
    private lateinit var binding: ActivityEntryBinding
    lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_entry)

        bottomNav = binding.bottomNavigationView

        navController = findNavController(R.id.nav_host_fragment_entry)


        setupNav()

        setupBottomNavigation()

        drawerLayout = binding.drawerLayout

        // Navigation UP
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationView, navController)
    }


    private fun setupNav() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.topMovieFragment -> showBottomNav()
                R.id.popularMovieFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        bottomNav.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        bottomNav.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        this.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        this.supportActionBar!!.show()
    }

    private fun setupBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
