package com.nikmaram.data.cache.di

import android.content.Context
import androidx.room.Room
import com.nikmaram.data.cache.database.AppDatabase
import com.nikmaram.data.cache.database.dao.ProductDao
import com.nikmaram.data.cache.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context):AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao{
        return appDatabase.getProductDao()
    }
}