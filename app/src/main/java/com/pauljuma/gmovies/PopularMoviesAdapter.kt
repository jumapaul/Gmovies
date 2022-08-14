package com.pauljuma.gmovies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pauljuma.gmovies.MyData.Movie

class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.MyViewHolder>() {

    private val movies: MutableList<Movie> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun getPopularMovies(items: List<Movie>) {
        this.movies.addAll(items)
        notifyDataSetChanged()

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            val image = itemView.findViewById<ImageView>(R.id.ivPhotos)

            Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}