package com.example.takenote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey
    val  id : UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title")
    val tile : String ,
    @ColumnInfo(name="description")
    val description : String,
    @ColumnInfo(name = "entry_date")
    val entryData : Date
)
