package com.example.lab_week_12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private val imageBaseUrl = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val poster: ImageView = findViewById(R.id.movie_poster)
        val title: TextView = findViewById(R.id.title_text)
        val overview: TextView = findViewById(R.id.overview_text)

        val movieTitle = intent.getStringExtra("title") ?: ""
        val movieOverview = intent.getStringExtra("overview") ?: ""
        val posterPath = intent.getStringExtra("poster_path") ?: ""

        title.text = movieTitle
        overview.text = movieOverview

        Glide.with(this)
            .load(imageBaseUrl + posterPath)
            .placeholder(R.mipmap.ic_launcher)
            .into(poster)
    }
}
