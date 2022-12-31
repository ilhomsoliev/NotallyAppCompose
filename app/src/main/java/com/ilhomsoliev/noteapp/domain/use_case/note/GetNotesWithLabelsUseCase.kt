package com.ilhomsoliev.noteapp.domain.use_case.note

import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNotesWithLabelsUseCase @Inject constructor(
    private val repository: NoteRepository
){
    operator fun invoke(): Flow<List<NoteWithLabels>> {
        return repository.getNotesWithLabels().map { notes ->
            notes.sortedBy { it.note.timestamp }
        }
    }
}