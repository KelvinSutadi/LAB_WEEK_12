package com.example.lab_week_12

import com.example.lab_week_12.api.MovieService
import com.example.lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "4bf75ebecbd25b57f0c8485e3b61c04e"

    // fetch movies using Flow
    fun fetchMovies(): Flow<List<Movie>> {
        return flow {
            emit(movieService.getPopularMovies(apiKey).results)
        }.flowOn(Dispatchers.IO)
    }
}
