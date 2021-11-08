package com.swapnesh.appmovie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swapnesh.appmovie.repo.MainRepo
import com.swapnesh.movies.view.ui.model.DataItem

import com.swapnesh.movies.view.ui.model.DataResponse



class MainViewModel(application: Application) : AndroidViewModel(application) {
    val mlLogin: LiveData<DataResponse> = MutableLiveData()
    val mldataitem: LiveData<DataItem> = MutableLiveData()

    fun getMovie(currentPage: Int) {
        MainRepo.getmovie(
            mlLogin as MutableLiveData<DataResponse>,currentPage)
    }

//    fun getAllTask(mContext: Context) {
//        DbRepo.getAllTask(
//            mldataitem as MutableLiveData<ArrayList<DataItem>>,
//            mContext
//        )
//    }
}