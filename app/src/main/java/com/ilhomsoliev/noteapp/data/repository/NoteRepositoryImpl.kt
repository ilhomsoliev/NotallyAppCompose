package com.ilhomsoliev.noteapp.data.repository

import com.ilhomsoliev.noteapp.data.data_source.NoteDao
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> = dao.getNotes()

    override fun getNotesWithLabels(): Flow<List<NoteWithLabels>> = dao.getNotesWithLabels()

    override suspend fun getNoteWithLabelsById(id: Int): NoteWithLabels =
        dao.getNoteWithLabelsById(id)

    override suspend fun getNote(id: Int): Note? = dao.getNoteById(id)

    override suspend fun insertNote(note: Note):Long {
       return dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override suspend fun deleteNoteLabelCrossRef(crossRef: NoteLabelCrossRef) {
        dao.deleteNoteLabelCrossRef(crossRef)
    }

    override suspend fun insertNoteLabelCrossRef(crossRef: NoteLabelCrossRef) {
        dao.insertNoteLabelCrossRef(crossRef)
    }

}