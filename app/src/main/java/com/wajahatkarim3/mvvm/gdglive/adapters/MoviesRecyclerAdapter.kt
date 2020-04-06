package com.wajahatkarim3.mvvm.gdglive.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.wajahatkarim3.mvvm.gdglive.databinding.MovieItemLayoutBinding
import com.wajahatkarim3.mvvm.model.MovieModel

class MoviesRecyclerAdapter(val onItemClick : (movie: MovieModel) -> Unit) : ListAdapter<MovieModel, MoviesRecyclerAdapter.MovieViewHolder>(MOVIE_DIFF_CALLBACK)
{
    private val moviesList = arrayListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var itemBinding = MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(getItem(position), onItemClick)
    }

    fun setMovies(movies: List<MovieModel>) {
        clearAllMovies()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

    fun clearAllMovies() {
        moviesList.clear()
    }

    inner class MovieViewHolder(val itemBinding: MovieItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(movie: MovieModel, onItemClick: (movie: MovieModel) -> Unit) {
            itemBinding.apply {
                imgMoviePoster.load(movie.posterPath)

                imgMoviePoster.setOnClickListener {
                    onItemClick.invoke(movie)
                }
            }
        }
    }

    companion object {
        private val MOVIE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}