package com.wajahatkarim3.mvvm.gdglive.ui.movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import com.wajahatkarim3.mvvm.gdglive.R
import com.wajahatkarim3.mvvm.gdglive.adapters.ActorsRecyclerAdapter
import com.wajahatkarim3.mvvm.gdglive.app.Constants
import com.wajahatkarim3.mvvm.gdglive.databinding.ActivityMovieDetailsBinding
import org.koin.android.ext.android.get

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var bi: ActivityMovieDetailsBinding
    private val viewModel by viewModels<MovieDetailsViewModel> {
        MovieDetailsViewModelFactory(get())
    }

    lateinit var recyclerAdapter: ActorsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
        initObservations()
        loadIntentData()

        viewModel.loadMovieActors()
    }

    fun setupViews()
    {
        // Toolbar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Recycler Adapter
        recyclerAdapter = ActorsRecyclerAdapter()
        bi.recyclerActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        bi.recyclerActors.adapter = recyclerAdapter
    }

    fun initObservations()
    {
        // Movie
        viewModel.currentMovie.observe(this, Observer { movie ->
            bi.apply {
                txtMovieTitle.text = movie.title
                txtPlot.text = movie.overview
                txtRating.text = movie.voteAverage.toString()
                txtReleaseDate.text = movie.release_date
                imgMoviePoster.load(Constants.IMAGE_URL + movie.posterPath) {
                    error(R.color.colorPrimary)
                    placeholder(R.color.colorAccent)
                }
            }
        })

        // Actors
        viewModel.actorsList.observe(this, Observer { actorsList ->
            recyclerAdapter.setItems(actorsList)
        })

        // UI
        viewModel.uiState.observe(this, Observer { state ->
            when(state)
            {
                ActorsLoadingState -> {
                    bi.progressActors.visibility = View.VISIBLE
                    bi.recyclerActors.visibility = View.GONE
                }

                ActorsContentState -> {
                    bi.progressActors.visibility = View.GONE
                    bi.recyclerActors.visibility = View.VISIBLE
                }

                is ErrorState -> {
                    bi.recyclerActors.visibility = View.GONE
                    bi.progressActors.visibility = View.GONE
                    bi.lblCast.visibility = View.GONE
                    Snackbar.make(bi.root, state.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun loadIntentData()
    {
        if (intent.hasExtra("movie"))
        {
            viewModel.setMovieFromIntent(intent.getParcelableExtra("movie"))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
        {
            supportFinishAfterTransition()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
