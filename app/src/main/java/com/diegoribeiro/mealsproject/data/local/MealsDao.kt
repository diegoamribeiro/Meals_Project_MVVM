package com.diegoribeiro.mealsproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categoriesEntity: CategoriesEntity)

    @Query("SELECT * FROM CATEGORIES_TABLE ORDER BY id ASC")
    fun readCategories(): Flow<List<CategoriesEntity>>

}