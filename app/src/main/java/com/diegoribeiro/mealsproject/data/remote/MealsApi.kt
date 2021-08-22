package com.diegoribeiro.mealsproject.data.remote

import com.diegoribeiro.mealsproject.data.model.Categories
import com.diegoribeiro.mealsproject.data.model.Meal
import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.data.model.Recipes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    @GET("categories.php")
    suspend fun getAllCategories(): Response<Categories>

    @GET("filter.php")
    suspend fun getAllMealsByCategory(@Query("c") category: String): Response<Meals>

    @GET("lookup.php")
    suspend fun getAllMealsById(@Query("i") id: String): Recipes

}