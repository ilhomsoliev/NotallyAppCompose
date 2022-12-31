package com.ilhomsoliev.noteapp.domain.use_case.note.get

import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetArchivedNotesWithLabelsUseCase @Inject constructor(
    private val repository: NoteRepository
){
    operator fun invoke(): Flow<List<NoteWithLabels>> {
        return repository.getArchivedNotesWithLabels().map { notes ->
            notes.sortedBy { it.note.timestamp }
        }
    }
}