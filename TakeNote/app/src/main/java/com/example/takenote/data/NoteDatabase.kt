package com.example.takenote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.takenote.converter.Converter
import com.example.takenote.model.Note

@Database(entities = [Note::class] , version = 1 , exportSchema = false)
@TypeConverters(Converter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao():NoteDataBaseDAO
}