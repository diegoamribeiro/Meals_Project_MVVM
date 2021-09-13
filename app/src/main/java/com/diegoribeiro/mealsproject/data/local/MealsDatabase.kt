package com.diegoribeiro.mealsproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [MealsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MealsTypeConverter::class)
abstract class MealsDatabase: RoomDatabase() {

    abstract fun mealsDao(): MealsDao

    companion object{
        @Volatile
        private var INSTANCE: MealsDatabase? = null

        fun getDatabase(context: Context): MealsDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealsDatabase::class.java,
                    "todo_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}