package com.beetlestance.movies.data.datasource.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.beetlestance.movies.data.datasource.AppTables
import com.beetlestance.movies.data.models.entities.Movies

@Dao
abstract class MoviesDao {

    @Insert
    abstract suspend fun insertAll(entities: List<Movies>)

    @Query(value = ALL_MOVIES_QUERY)
    abstract suspend fun allMovies(): List<Movies>

    companion object {

        private const val ALL_MOVIES_QUERY = "SELECT * FROM ${AppTables.MOVIES_TABLE}"

    }

}