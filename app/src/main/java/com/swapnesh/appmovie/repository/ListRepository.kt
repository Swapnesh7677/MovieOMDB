package com.triologic.jewel_cliq.repository

import android.content.Context
import android.util.Log
import com.swapnesh.movies.view.ui.model.DataItem
import com.triologic.jewel_cliq.room.movieDatabase


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ListRepository {

    companion object {

        var listDatabase: movieDatabase? = null

        var dataitemlist: ArrayList<DataItem> =ArrayList()

        fun initializeDB(context: Context): movieDatabase {
            return movieDatabase.getDbClient(context)
        }



        fun insertData(
            context: Context,
            imdbID: String,
            title: String,
            year: String,
            type: String,
            poster: String?,
            createdAt: Long,  ) {

            listDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val userDetails = DataItem(imdbID,title,year,type,poster,createdAt)
                listDatabase!!.movieDao().insertData(userDetails)
            }

        }

        fun getLists(context: Context) : ArrayList<DataItem>? {

            listDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                dataitemlist = listDatabase!!.movieDao().getAll() as ArrayList<DataItem>


            }

            return dataitemlist
        }



    }
}