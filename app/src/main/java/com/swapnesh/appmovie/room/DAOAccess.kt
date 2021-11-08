package com.triologic.jewel_cliq.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.swapnesh.movies.view.ui.model.DataItem

@Dao
interface DAOAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(datamovie: DataItem)

    @Query("SELECT * FROM movies")
      fun getAll(): List<DataItem>



}