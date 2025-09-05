package com.example.pregnancyvitalstracker.dataclass

import android.content.Context
import androidx.room.Room
import com.example.submissiondemo.dataStore.VitalsDio
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): VitalsDatabase {
        return Room.databaseBuilder(
            appContext,
            VitalsDatabase::class.java,
            name = "vitals_database"
        ).build()
    }

    @Provides
    fun provideVitalsDao(db: VitalsDatabase): VitalsDio = db.vitalsDao()
}