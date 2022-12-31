package com.ilhomsoliev.noteapp.domain.use_case.note

import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val repository:NoteRepository
){
    suspend operator fun invoke(
        note: NoteWithLabels
    ){
        val newId = repository.insertNote(note.note)
        note.labels.forEach{
            repository.insertNoteLabelCrossRef(NoteLabelCrossRef(newId.toInt(),it.labelId))
        }
    }

}