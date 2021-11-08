package com.swapnesh.appmovie

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.swapnesh.appmovie.activity.MovieDetailsActivity
import com.swapnesh.appmovie.databinding.ActivityMainBinding
import com.swapnesh.appmovie.interfaces.OnListBsItemClickListener
import com.swapnesh.appmovie.viewmodel.MainViewModel
import com.swapnesh.movies.view.ui.model.DataItem
import com.swapnesh.movies.view.ui.model.DataResponse
import com.swapnesh.movies.view.ui.util.CheckValidation
import com.triologic.jewel_cliq.repository.ListRepository
import com.triologic.jewel_cliq.room.movieDatabase
import com.triologic.jewel_cliq.ui.adapter.ItemListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(),OnListBsItemClickListener {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainVm: MainViewModel
    private var listData: ArrayList<DataItem> = ArrayList()
    private var currentPage: Int = 1
    private var isFetchIngPageData = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(false)
        }
        mainVm = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(MainViewModel::class.java)
        ListRepository.initializeDB(this@MainActivity)
         mainVm.getMovie(currentPage)

        val listObserver = Observer<DataResponse> { movie ->

            try {
                if(movie!=null ){
                    listData.addAll(movie.Search)

                    isFetchIngPageData = false
                    recyclemovieList(listData)
                    for (i in 0 until listData.size) {
                        ListRepository.insertData(this, listData.get(i).imdbID, listData.get(i).title, listData.get(i).year, listData.get(i).type, listData.get(i).poster,listData.get(i).createdAt)
                    }
                }else{
                    try {
                        CoroutineScope(Dispatchers.IO).launch {
                            listData = ListRepository.initializeDB(this@MainActivity).movieDao().getAll() as ArrayList<DataItem>
                            isFetchIngPageData = true
                            recyclemovieList(listData)
                        }
                    } catch (e: Exception) {
                    }
                }
            } catch (e: Exception) {

            }
            recyclemovieList(listData)

        }

         mainVm.mlLogin.observe(this, listObserver)


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclemovieList(listData: ArrayList<DataItem>) {
        binding.rvMovieList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this@MainActivity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvMovieList.layoutManager = GridLayoutManager(this@MainActivity, 2)


            if (binding.rvMovieList.adapter != null) {
                binding.rvMovieList.adapter!!.notifyDataSetChanged()
                binding.rvMovieList.adapter =  ItemListAdapter(listData, this)
            } else {
                binding.rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                    }

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val layoutManager = LinearLayoutManager::class.java.cast( binding.rvMovieList.layoutManager)
                        val totalItemCount = layoutManager.itemCount
                        val lastVisible  = layoutManager.findLastVisibleItemPosition()
                        val endHasBeenReached = lastVisible + 5 >= totalItemCount
                        if (totalItemCount > 0 && endHasBeenReached) {
                            if (!isFetchIngPageData) {
                                isFetchIngPageData = true
                                currentPage++
                                mainVm.getMovie(currentPage)
                            }
                        }
                    }
                })

                binding.rvMovieList.adapter =  ItemListAdapter(listData, this)
                binding.rvMovieList.adapter?.notifyDataSetChanged()

            }

    }





    override fun onListBsItemClicked(index: Int) {
        val intent = Intent(applicationContext, MovieDetailsActivity::class.java)
        intent.putExtra("IMDBID", listData.get(index).imdbID)
        startActivity(intent)

    }


}


