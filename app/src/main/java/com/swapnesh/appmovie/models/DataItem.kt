package com.swapnesh.movies.view.ui.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class DataItem(

    @SerializedName("imdbID")
    var imdbID: String,
    @SerializedName("Title")
    var title: String,
    @SerializedName("Year")
    var year: String,
    @SerializedName("Type")
    var type: String,
    @SerializedName("Poster")
    var poster: String?,
    var createdAt: Long
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null
}