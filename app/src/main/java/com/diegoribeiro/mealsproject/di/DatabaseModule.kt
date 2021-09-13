package com.diegoribeiro.mealsproject.di

import android.content.Context
import androidx.room.Room
import com.diegoribeiro.mealsproject.data.local.MealsDatabase
import com.diegoribeiro.mealsproject.utils.constants.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
       context,
       MealsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providesDao(database: MealsDatabase) = database.mealsDao()


}