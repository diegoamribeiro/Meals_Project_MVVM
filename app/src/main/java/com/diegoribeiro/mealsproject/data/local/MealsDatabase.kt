package com.diegoribeiro.mealsproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [MealsEntity::class, CategoriesEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(MealsTypeConverter::class)
abstract class MealsDatabase: RoomDatabase() {

    abstract fun mealsDao(): MealsDao

}