package com.ilhomsoliev.noteapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ilhomsoliev.noteapp.data.data_source.NoteDatabase
import com.ilhomsoliev.noteapp.data.repository.LabelRepositoryImpl
import com.ilhomsoliev.noteapp.data.repository.NoteRepositoryImpl
import com.ilhomsoliev.noteapp.domain.repository.Preferences
import com.ilhomsoliev.noteapp.data.repository.PreferencesImpl
import com.ilhomsoliev.noteapp.domain.repository.LabelRepository
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "notes_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideLabelRepository(db: NoteDatabase): LabelRepository {
        return LabelRepositoryImpl(db.labelDao)
    }

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext app: Context): Preferences = PreferencesImpl(app)
}