package com.pauljuma.gmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.pauljuma.gmovies.MyData.MoviesResponse
import com.pauljuma.gmovies.Network.GmovieApi
import com.pauljuma.gmovies.Network.GmovieEndPoints
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myAdapter = TopRatedMoviesAdapter()

        val request = GmovieApi.buildService(GmovieEndPoints::class.java)
        val call = request.getTopRatedMovies(getString(R.string.api_key))

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {

                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE

                    myAdapter.addMovies(response.body()?.results ?: emptyList())

                    rvTopRatedMovies.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        adapter = myAdapter
                    }
                }

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d("========>", "onResponse: ${t.message}")
                t.printStackTrace()
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()

            }
        })

        val adapter2 = UpcomingMoviesAdapter()
        val call1 = request.getUpcomingMovies(getString(R.string.api_key))

        call1.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE

                    Log.d("=============>", "onResponse: ${response.body()}")

                    adapter2.addMovies(response.body()?.results!!)

                    rvUpcomingMovies.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )

                        adapter = adapter2
                    }
                }

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()

            }
        })

        val adapter3 = PopularMoviesAdapter()
        val call3 = request.getPopularMovies(getString(R.string.api_key))

        call3.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE

                    adapter3.getPopularMovies(response.body()?.results!!)

                    rvPopularMovies.apply {
                        setHasFixedSize(true)

                        layoutManager = LinearLayoutManager(
                            this@MainActivity,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )

                        adapter = adapter3
                    }

                }

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()

            }
        })

    }
}