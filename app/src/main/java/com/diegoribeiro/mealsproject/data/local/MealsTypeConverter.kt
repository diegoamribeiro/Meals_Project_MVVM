package com.diegoribeiro.mealsproject.data.local

import androidx.room.TypeConverter
import com.diegoribeiro.mealsproject.data.model.Category
import com.diegoribeiro.mealsproject.data.model.Meal
import com.diegoribeiro.mealsproject.data.model.Meals
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealsTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun mealsToString(meal: Meals): String{
        return gson.toJson(meal)
    }

    @TypeConverter
    fun stringToMeals(data: String): Meals{
        val listType = object : TypeToken<Meals>(){}.type
        return gson.fromJson(data, listType)
    }
}