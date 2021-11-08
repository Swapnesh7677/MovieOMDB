package com.swapnesh.movies.view.ui.Retrofit



import android.content.Context
import com.swapnesh.movies.view.ui.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object JfServer {
    private var context : Context? = null
    fun init(context : Context) { JfServer.context = context   }
    private var retrofit: Retrofit? = null

    fun getJfServer(): Retrofit {
        if (retrofit == null) {
            val client = OkHttpClient().newBuilder()
            client.connectTimeout(1, TimeUnit.MINUTES)
            client.readTimeout(30, TimeUnit.SECONDS)
            client.writeTimeout(30, TimeUnit.SECONDS)
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }

        return retrofit!!
    }



}