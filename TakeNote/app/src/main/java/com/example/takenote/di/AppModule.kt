package com.example.takenote.di

import android.content.Context
import androidx.room.Room
import com.example.takenote.data.NoteDataBaseDAO
import com.example.takenote.data.NoteDatabase
import com.example.takenote.model.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_components_SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDataBaseDAO = noteDatabase.noteDao()

    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase
    = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes"
    )
        .fallbackToDestructiveMigration()
        .build()
}