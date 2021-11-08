package com.swapnesh.movies.view.ui.Retrofit

import com.swapnesh.appmovie.models.MoiveDetailResponce
import com.swapnesh.movies.view.ui.model.DataItem
import com.swapnesh.movies.view.ui.model.DataResponse
import com.swapnesh.movies.view.ui.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.*


interface JfClient {


    @GET("?apiKey=" +API_KEY)
    fun fetchMovies(@Query("s") search : String,
                    @Query("page") page : Int ): Call<DataResponse>


    @GET("?apiKey=" +API_KEY)
    fun fetchMoviesDetails(@Query("i") imbdID : String ): Call<MoiveDetailResponce>





}