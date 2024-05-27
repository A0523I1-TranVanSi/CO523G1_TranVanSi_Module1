package com.example.takenote.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.takenote.model.Note


@Dao
interface NoteDataBaseDAO {
    @Query("select * from notes")
    fun getNotes(): kotlinx.coroutines.flow.Flow<List<Note>>

    @Query("select * from notes where id =:id")
    suspend fun getNoteById(id : String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query("delete from notes")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note)
}