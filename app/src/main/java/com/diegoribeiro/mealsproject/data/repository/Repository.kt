package com.diegoribeiro.mealsproject.data.repository

import com.diegoribeiro.mealsproject.data.model.Meal
import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.data.remote.RemoteClient
import retrofit2.Response
import retrofit2.Retrofit

object Repository {

    suspend fun getAllCategories() = RemoteClient.getService().getAllCategories()

    suspend fun getAllMealsByCategory(category: String): Response<Meals>{
       return RemoteClient.getService().getAllMealsByCategory(category)
    }

    suspend fun getMealById(id: String): Response<Meal>{
        return RemoteClient.getService().getAllMealsById(id)
    }

}