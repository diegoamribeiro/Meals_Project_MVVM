package com.diegoribeiro.mealsproject.data.remote

import com.diegoribeiro.mealsproject.data.model.*
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val mealsApi: MealsApi
){
    suspend fun getCategories(): Response<Categories>{
        return mealsApi.getAllCategories()
    }

    suspend fun getAllMealsCategory(category: String): Response<Meals>{
        return mealsApi.getAllMealsByCategory(category)
    }

    suspend fun getAllMealsById(id: String): Response<Recipes>{
        return mealsApi.getAllMealsById(id)
    }

}