package com.wajahatkarim3.mvvm.gdglive.ui.home

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.wajahatkarim3.mvvm.gdglive.R
import com.wajahatkarim3.mvvm.gdglive.adapters.MoviesRecyclerAdapter
import com.wajahatkarim3.mvvm.gdglive.databinding.ActivityHomeBinding
import com.wajahatkarim3.mvvm.gdglive.model.MovieModel
import com.wajahatkarim3.mvvm.gdglive.ui.movie_details.MovieDetailsActivity
import org.koin.android.ext.android.get
import org.koin.ext.getScopeName

class HomeActivity : AppCompatActivity() {

    private lateinit var bi: ActivityHomeBinding

    lateinit var recyclerAdapter: MoviesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
        loadDummyData()
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

    fun loadDummyData()
    {
        // Show Loading
        bi.recyclerMoviesList.visibility = View.GONE
        bi.progressMovies.visibility = View.VISIBLE

        Handler().postDelayed({

            var list = arrayListOf<MovieModel>()
            list.add(MovieModel(voteAverage = 4.6f, id = 1001, title = "Mad Max Fury Road", posterPath = "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg"))
            list.add(MovieModel(voteAverage = 4.6f, id = 1002, title = "Ad Astra", posterPath = "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"))
            list.add(MovieModel(voteAverage = 4.6f, id = 1003, title = "Bad Boys for Life", posterPath = "/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg"))
            list.add(MovieModel(voteAverage = 4.6f, id = 1004, title = "Pocahontas II: Journey to a New World", posterPath = "/gAnmNjw1TCTzS702mj1OwwmXBpM.jpg"))
            list.add(MovieModel(voteAverage = 4.6f, id = 1005, title = "Star Wars: The Rise of Skywalker", posterPath = "/db32LaOibwEliAmSL2jjDF6oDdj.jpg"))
            recyclerAdapter.setMovies(list)

            runOnUiThread {
                // Hide Loading
                bi.recyclerMoviesList.visibility = View.VISIBLE
                bi.progressMovies.visibility = View.GONE
            }

        }, 4000)
    }
}
