package com.beetlestance.movies.data.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beetlestance.movies.data.datasource.AppTables

@Entity(tableName = AppTables.MOVIES_TABLE)
data class Movies(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,

    @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "posterImage") val posterImage: String
)