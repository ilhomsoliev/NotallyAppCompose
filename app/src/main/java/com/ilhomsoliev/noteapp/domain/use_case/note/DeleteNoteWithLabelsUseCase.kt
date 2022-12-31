package com.ilhomsoliev.noteapp.domain.use_case.note

import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteWithLabelsUseCase@Inject constructor(
    private val repository: NoteRepository
){
    suspend operator fun invoke(
        note: NoteWithLabels
    ){
        repository.deleteNote(note.note)
        note.labels.forEach {
            repository.deleteNoteLabelCrossRef(NoteLabelCrossRef(note.note.noteId?:-1,it.labelId))
        }
    }
}