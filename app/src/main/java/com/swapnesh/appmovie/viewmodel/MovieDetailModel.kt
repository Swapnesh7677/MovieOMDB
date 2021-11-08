package com.swapnesh.appmovie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swapnesh.appmovie.models.MoiveDetailResponce
import com.swapnesh.appmovie.repo.MainRepo




class MovieDetailModel(application: Application) : AndroidViewModel(application) {
    val mldetail: LiveData<MoiveDetailResponce> = MutableLiveData()

    fun getDetailMovie(imdbid: String) {
        MainRepo.getmoviedetail(mldetail as MutableLiveData<MoiveDetailResponce>,imdbid)
    }



}