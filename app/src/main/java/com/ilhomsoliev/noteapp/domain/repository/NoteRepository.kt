package com.ilhomsoliev.noteapp.domain.repository

import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    fun getNotesWithLabels(): Flow<List<NoteWithLabels>>
    suspend fun getNoteWithLabelsById(id: Int): NoteWithLabels
    suspend fun getNote(id: Int): Note?
    suspend fun insertNote(note: Note):Long
    suspend fun deleteNote(note: Note)
    suspend fun deleteNoteLabelCrossRef(crossRef: NoteLabelCrossRef)
    suspend fun insertNoteLabelCrossRef(crossRef: NoteLabelCrossRef)
}