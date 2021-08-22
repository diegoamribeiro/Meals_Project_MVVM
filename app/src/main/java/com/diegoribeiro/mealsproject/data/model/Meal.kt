package com.diegoribeiro.mealsproject.data.model
import java.io.Serializable


data class Meal(
    val dateModified: Any,
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strMeal: String,
    val strMealThumb: String,
    val strSource: Any,
    val strTags: String,
    val strYoutube: String
): Serializable
