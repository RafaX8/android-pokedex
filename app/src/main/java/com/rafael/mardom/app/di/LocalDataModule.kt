package com.rafael.mardom.app.di

import android.content.Context
import androidx.room.Room
import com.rafael.mardom.app.data.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java, "PokedexDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}