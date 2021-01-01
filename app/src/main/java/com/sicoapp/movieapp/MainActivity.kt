package com.sicoapp.movieapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sicoapp.movieapp.data.database.DAOAccess
import com.sicoapp.movieapp.repository.SmileyRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dao : DAOAccess

    @Inject
    lateinit var smileyRepository: SmileyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("daoAcces", "${dao.hashCode()}")
        Log.d("smileyRepository", "${smileyRepository.hashCode()}")

        bottomNavigationView.setupWithNavController(NavHostFragment.findNavController(this.nav_host_fragment))
    }
}