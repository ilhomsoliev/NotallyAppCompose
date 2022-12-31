package com.ilhomsoliev.noteapp.domain.use_case.note

import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class UnarchiveNoteUseCase @Inject constructor(
    private val repository: NoteRepository
){
    suspend operator fun invoke(
        note: NoteWithLabels
    ){
        note.note.isArchived = false
        repository.insertNote(note.note)
    }
}