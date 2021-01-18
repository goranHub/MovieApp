package com.sicoapp.movieapp.ui.movie.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
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

        setupBottomNavigation()



        drawerLayout = binding.drawerLayout

        // Navigation UP
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navigationView, navController)
    }

    override fun onResume() {
        super.onResume()
        this.supportActionBar!!.hide()
        this.bottomNav.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        this.supportActionBar!!.show()
        this.bottomNav.visibility = View.VISIBLE
    }

    private fun setupBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}
