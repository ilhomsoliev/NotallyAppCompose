package com.ilhomsoliev.noteapp.domain.use_case.note

import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject


class GetNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(
        id:Int
    ): Note? = repository.getNote(id)

}