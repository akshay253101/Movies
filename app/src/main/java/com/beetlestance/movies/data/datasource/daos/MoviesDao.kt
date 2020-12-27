package com.beetlestance.movies.data.datasource.daos

import androidx.room.Dao
import androidx.room.Insert
import com.beetlestance.movies.data.datasource.AppTables
import com.beetlestance.movies.data.models.entities.Movies

@Dao
abstract class MoviesDao {

    @Insert
    abstract suspend fun insertAll(entities: List<Movies>)

    companion object {

        private const val ALL_MOVIES_QUERY = "SELECT * FROM ${AppTables.MOVIES_TABLE}"

    }

}