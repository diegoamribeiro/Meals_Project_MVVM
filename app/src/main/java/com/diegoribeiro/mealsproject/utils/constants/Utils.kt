package com.diegoribeiro.mealsproject.utils.constants

import com.diegoribeiro.mealsproject.data.local.MealsEntity
import com.diegoribeiro.mealsproject.data.model.Meal

fun Meal.convertMealToMealsEntity(){
    this.copy()

}