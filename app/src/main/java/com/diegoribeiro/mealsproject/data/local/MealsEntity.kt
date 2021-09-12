package com.diegoribeiro.mealsproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diegoribeiro.mealsproject.data.model.Meal
import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.utils.constants.Constants.MEALS_TABLE

@Entity(tableName = MEALS_TABLE)
class MealsEntity(
    var meals: Meals
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}