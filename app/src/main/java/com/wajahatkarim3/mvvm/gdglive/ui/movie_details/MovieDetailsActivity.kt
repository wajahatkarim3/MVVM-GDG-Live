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
import com.wajahatkarim3.mvvm.gdglive.model.MovieModel
import org.koin.android.ext.android.get

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var bi: ActivityMovieDetailsBinding

    lateinit var recyclerAdapter: ActorsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
        loadIntentData()
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

    fun loadIntentData()
    {
        if (intent.hasExtra("movie"))
        {
            var movie = intent.getParcelableExtra("movie") as MovieModel
            bi.apply {
                txtMovieTitle.text = movie.title
                txtRating.text = movie.voteAverage.toString()
                txtReleaseDate.text = movie.release_date
                imgMoviePoster.load(Constants.IMAGE_URL + movie.posterPath) {
                    error(R.color.colorPrimary)
                    placeholder(R.color.colorAccent)
                }
            }

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
