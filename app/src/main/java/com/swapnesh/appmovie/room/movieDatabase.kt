package com.triologic.jewel_cliq.room

import android.content.Context
import androidx.room.*
import com.swapnesh.movies.view.ui.model.DataItem



@Database(entities = [DataItem::class], version = 1, exportSchema = false)
abstract class movieDatabase : RoomDatabase() {

    abstract fun movieDao(): DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: movieDatabase? = null

        fun getDbClient(context: Context): movieDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, movieDatabase::class.java, "MOVIE_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}