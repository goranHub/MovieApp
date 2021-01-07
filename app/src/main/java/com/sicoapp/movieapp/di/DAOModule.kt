package com.sicoapp.movieapp.di

import com.sicoapp.movieapp.data.database.DAOAccess
import com.sicoapp.movieapp.data.repository.SmileyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * @author ll4
 * @date 12/31/2020
 */

@Module
@InstallIn(ApplicationComponent::class)
object DAOModule {

    @Provides
    @Singleton
    fun provideSmileyRepository(
        dao: DAOAccess
    ): SmileyRepository {
        return SmileyRepository(dao)
    }
}