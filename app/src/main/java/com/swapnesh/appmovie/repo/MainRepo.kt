package com.swapnesh.appmovie.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.swapnesh.appmovie.models.MoiveDetailResponce
import com.swapnesh.movies.view.ui.Retrofit.JfClient
import com.swapnesh.movies.view.ui.Retrofit.JfServer
import com.swapnesh.movies.view.ui.model.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainRepo {
    private var jfClient: JfClient = JfServer.getJfServer().create(JfClient::class.java)

    fun getmovie(ml_login: MutableLiveData<DataResponse>, currentPage: Int) {

        var login = DataResponse()
        val apiName = "Movies"
        val req: Call<DataResponse> = jfClient.fetchMovies("movies",currentPage)
        Log.e("re[o", "URL -- >" + req.request().url())
        req.enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful) {

                    if (response.body() != null) {
                        login = response.body()!!
                        Log.e("TAG", "onResponse " +login.toString())
                        ml_login.value = login

                    } else {

                    }

                } else {
                    ml_login.value = null
                }

            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                t.printStackTrace()
                ml_login.value = null
                Log.e("TAG", "onFailure " + t.localizedMessage)


            }

        })


    }


    fun getmoviedetail(ml_movieDetails: MutableLiveData<MoiveDetailResponce>,    imdbID: String,) {


        val apiName = "Movies"
        val req: Call<MoiveDetailResponce> = jfClient.fetchMoviesDetails(imdbID)
        Log.e("re[o", "URL -- >" + req.request().url())
        req.enqueue(object : Callback<MoiveDetailResponce> {
            override fun onResponse(call: Call<MoiveDetailResponce>, response: Response<MoiveDetailResponce>) {
                if (response.isSuccessful) {

                    if (response.body() != null) {
                       val datamovieDetail :MoiveDetailResponce = response.body()!!
                        Log.e("TAG", "onResponse " +datamovieDetail.toString())
                        ml_movieDetails.value = datamovieDetail

                    } else {

                    }

                } else {
                    //ml_movieDetails.value =
                }

            }

            override fun onFailure(call: Call<MoiveDetailResponce>, t: Throwable) {
                t.printStackTrace()
                Log.e("TAG", "onFailure " + t.localizedMessage)

              //  ml_movieDetails.value = datamovieDetail
            }

        })


    }




}