package com.example.lab_week_12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar

class MovieViewModel(private val movieRepository: MovieRepository)
    : ViewModel() {

    private val _popularMovies = MutableStateFlow(
        emptyList<Movie>()
    )
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchMovies()
                .map { movies ->

                    val currentYear =
                        Calendar.getInstance()
                            .get(Calendar.YEAR)
                            .toString()

                    movies
                        .filter { movie ->
                            movie.releaseDate?.startsWith(currentYear) == true
                        }
                        .sortedByDescending { it.popularity }   // filtering by popularity
                }
                .catch {
                    _error.value = "An exception occurred: ${it.message}"
                }
                .collect {
                    _popularMovies.value = it   // masuk ke stateflow
                }

        }
    }
}