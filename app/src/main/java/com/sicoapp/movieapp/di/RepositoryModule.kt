package com.sicoapp.movieapp.di

import com.sicoapp.movieapp.data.database.DataBaseDataSource
import com.sicoapp.movieapp.data.remote.NetworkDataSource
import com.sicoapp.movieapp.domain.IRepository
import com.sicoapp.movieapp.domain.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * @author ll4
 * @date 1/20/2021
 */
@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        networkDataSource: NetworkDataSource,
        databaseDataSource: DataBaseDataSource
    ): IRepository {
        return RepositoryImpl(
            networkDataSource = networkDataSource,
            databaseDataSource = databaseDataSource
        )
    }
}