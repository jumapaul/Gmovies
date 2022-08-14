package com.pauljuma.gmovies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pauljuma.gmovies.MyData.Movie

class TopRatedMoviesAdapter : RecyclerView.Adapter<TopRatedMoviesAdapter.MoviesViewHolder>() {

    private val movies: MutableList<Movie> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addMovies(items: List<Movie>) {
        this.movies.addAll(items)
        notifyDataSetChanged()

    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movies: Movie) {
            val images = itemView.findViewById<ImageView>(R.id.ivPhotos)
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movies.poster_path}").into(images)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview, parent, false)
        return MoviesViewHolder(view)

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        holder.bind(movies[position])

    }

    override fun getItemCount(): Int {

        return movies.size

    }
}