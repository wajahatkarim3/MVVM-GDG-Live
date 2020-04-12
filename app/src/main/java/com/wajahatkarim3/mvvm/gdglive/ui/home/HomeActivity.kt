package com.wajahatkarim3.mvvm.gdglive.ui.home

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.wajahatkarim3.mvvm.gdglive.R
import com.wajahatkarim3.mvvm.gdglive.adapters.MoviesRecyclerAdapter
import com.wajahatkarim3.mvvm.gdglive.databinding.ActivityHomeBinding
import com.wajahatkarim3.mvvm.gdglive.ui.movie_details.MovieDetailsActivity
import com.wajahatkarim3.mvvm.gdglive.ui.movie_details.MovieDetailsUiState
import org.koin.android.ext.android.get
import org.koin.ext.getScopeName

class HomeActivity : AppCompatActivity() {

    private lateinit var bi: ActivityHomeBinding

    // Default ViewModel without constructor parameters
    // private val viewModel by viewModels<HomeViewModel>()

    // ViewModel by Factory
    private val viewModel by viewModels<HomeViewModel>() {
        HomeViewModelFactory(get())
    }


    lateinit var recyclerAdapter: MoviesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bi.root)

        // ViewModel initialization
        // val tempViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        setupViews()
        initObservations()

        viewModel.loadMovies()
    }

    fun setupViews()
    {
        // Recycler Adapter
        recyclerAdapter = MoviesRecyclerAdapter { movie, itemBinding ->
            var intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("movie", movie)
            var options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, itemBinding.imgMoviePoster, "moviePoster")
            startActivity(intent, options.toBundle())
        }

        // RecyclerView
        bi.recyclerMoviesList.layoutManager = GridLayoutManager(this, 2)
        bi.recyclerMoviesList.setHasFixedSize(true)
        bi.recyclerMoviesList.adapter = recyclerAdapter
    }

    fun initObservations()
    {
        // This is where UI State is being observed from the ViewModel
        viewModel.uiState.observe(this, Observer { state ->
            when(state)
            {
                LoadingState -> {
                    bi.recyclerMoviesList.visibility = View.GONE
                    bi.progressMovies.visibility = View.VISIBLE
                    bi.txtMessage.visibility = View.GONE
                }

                MoviesListState -> {
                    bi.recyclerMoviesList.visibility = View.VISIBLE
                    bi.progressMovies.visibility = View.GONE
                    bi.txtMessage.visibility = View.GONE
                }

                is NoMoviesState -> {
                    bi.recyclerMoviesList.visibility = View.GONE
                    bi.progressMovies.visibility = View.GONE
                    bi.txtMessage.visibility = View.VISIBLE
                    bi.txtMessage.text = "No Movies Found!"
                }

                is ErrorState -> {
                    bi.recyclerMoviesList.visibility = View.GONE
                    bi.progressMovies.visibility = View.GONE
                    bi.txtMessage.visibility = View.VISIBLE
                    bi.txtMessage.text = state.message
                }
            }
        })

        viewModel.moviesList.observe(this, Observer { list ->
            recyclerAdapter.setMovies(list)
        })
    }
}
