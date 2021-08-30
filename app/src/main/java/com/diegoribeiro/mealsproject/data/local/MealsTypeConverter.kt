package com.diegoribeiro.mealsproject.data.local

import androidx.room.TypeConverter
import com.diegoribeiro.mealsproject.data.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealsTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun categoriesToString(category:Category):String{
        return gson.toJson(category)
    }

    @TypeConverter
    fun stringToCategory(data: String): Category{
        val listType = object : TypeToken<Category>(){}.type
        return gson.fromJson(data, listType)
    }

}