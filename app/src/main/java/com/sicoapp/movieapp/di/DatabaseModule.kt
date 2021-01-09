package com.sicoapp.movieapp.di

import android.content.Context
import androidx.room.Room
import com.sicoapp.movieapp.data.database.SmileyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        SmileyDatabase::class.java,
        "smiley_db"
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: SmileyDatabase) = db.movieDao()
}








