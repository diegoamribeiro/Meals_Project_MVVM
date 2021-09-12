package com.diegoribeiro.mealsproject.data.repository

import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.data.model.Recipes
import com.diegoribeiro.mealsproject.data.di.NetworkModule
import retrofit2.Response

object Repository {

    suspend fun getAllCategories() = NetworkModule.getService().getAllCategories()

    suspend fun getAllMealsByCategory(category: String): Response<Meals>{
       return NetworkModule.getService().getAllMealsByCategory(category)
    }

    suspend fun getMealById(id: String): Response<Recipes>{
        return NetworkModule.getService().getAllMealsById(id)
    }

}