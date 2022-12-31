package com.ilhomsoliev.noteapp.domain.use_case.note

import androidx.compose.runtime.Composable
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteWithLabelsByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(
        id:Int
    ): NoteWithLabels = repository.getNoteWithLabelsById(id)

}