package com.diegoribeiro.mealsproject.data.local

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Dao
class LocalDataSource @Inject constructor(
    private val mealsDao: MealsDao
){

    fun readDatabase(): Flow<List<MealsEntity>>{
        return mealsDao.readMeals()
    }

    suspend fun insertMeals(mealsEntity: MealsEntity){
        mealsDao.insertMeal(mealsEntity)
    }
}