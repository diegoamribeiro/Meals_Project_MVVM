package com.diegoribeiro.mealsproject.data.local

import android.content.Context
import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.data.model.Recipes
import com.diegoribeiro.mealsproject.data.remote.RemoteClient
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class LocalRepository(private val db: MealsDao) {

    fun getAllCategories() = db.readCategories()

//    suspend fun getAllMealsByCategory(category: String): Response<Meals>{
//       return RemoteClient.getService().getAllMealsByCategory(category)
//    }
//
//    suspend fun getMealById(id: String): Response<Recipes>{
//        return RemoteClient.getService().getAllMealsById(id)
//    }

}