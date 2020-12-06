package com.sicoapp.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sicoapp.movieapp.data.api.ApiClient
import com.sicoapp.movieapp.data.api.MovieApiService
import com.sicoapp.movieapp.data.response.fightClub.MoviesResponse
import com.sicoapp.movieapp.data.response.topRated.AboveTopRated
import com.sicoapp.movieapp.ui.TopMoviesViewModel
import com.sicoapp.movieapp.ui.adapter.TopMovieAdapter
import com.sicoapp.movieapp.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topMovieAdapter = TopMovieAdapter()
        val recyclerView  = findViewById<RecyclerView>(R.id.recylerViewActivty)
        recyclerView.layoutManager = LinearLayoutManager(this)

        TopMoviesViewModel().retrofitCall(recyclerView);
    }
}