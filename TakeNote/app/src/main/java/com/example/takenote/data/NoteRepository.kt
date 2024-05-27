package com.example.takenote.data

import com.example.takenote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDataBaseDAO) {
    suspend fun addNote(note:Note) = noteDatabaseDao.insert(note)
    suspend fun updateNote(note:Note) = noteDatabaseDao.update(note)
    suspend fun deleteNote(note:Note) = noteDatabaseDao.delete(note)
    suspend fun deleteAllNote() = noteDatabaseDao.deleteAll()
    fun getAllNote() : Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO)
}