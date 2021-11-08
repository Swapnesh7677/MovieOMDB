package com.swapnesh.appmovie.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.swapnesh.appmovie.databinding.ActivityMovieDetailsBinding
import com.swapnesh.appmovie.models.MoiveDetailResponce
import com.swapnesh.appmovie.viewmodel.MovieDetailModel

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMovieDetailsBinding
    private lateinit var mainVm: MovieDetailModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        var imdbid: String = intent.getStringExtra("IMDBID").toString()
        Log.d("MovieDetailsActivity", "imdbid " +imdbid)

        mainVm = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MovieDetailModel::class.java)

        mainVm.getDetailMovie(imdbid)

        val detailObserver = Observer<MoiveDetailResponce> { movie ->
            Log.d("MovieDetailsActivity", "detailObserver " +movie.toString())
            Log.d("MovieDetailsActivity", "detailObserver " +movie.Poster)

            updateUI(movie)
        }

        mainVm.mldetail.observe(this, detailObserver)

    }

    private fun updateUI(movie: MoiveDetailResponce) {

        Glide.with(this).load(movie.Poster).into(binding.ivBackdrop);
        Glide.with(this).load(movie.Poster).into(binding.ivPoster);
        binding.tvVoteAverage.text = movie.imdbRating
        binding.tvMovieTitleValue.text = movie.Title
        binding.tvGnreValue.text = movie.Genre
        binding.tvDuration.text = movie.Runtime
        binding.tvDescriptionTitle.text = movie.Plot
        binding.tvdiretorValue.text = movie.Director
        binding.tvwriterValue.text = movie.Writer
        binding.tvactorValue.text = movie.Actors
        binding.tvreleasedateValue.text = movie.Released
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}