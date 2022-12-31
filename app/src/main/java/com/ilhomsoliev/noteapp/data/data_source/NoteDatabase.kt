package com.ilhomsoliev.noteapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef

@Database(
    entities = [Note::class, Label::class,NoteLabelCrossRef::class],
    version = 8,
)
abstract class NoteDatabase:RoomDatabase() {
    abstract val noteDao:NoteDao
    abstract val labelDao: LabelDao

}