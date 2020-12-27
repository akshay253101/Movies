package com.beetlestance.movies.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beetlestance.movies.data.datasource.daos.MoviesDao
import com.beetlestance.movies.data.models.entities.Movies

@Database(entities = [Movies::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}

internal object AppTables {

    const val MOVIES_TABLE: String = "movies_table"

}