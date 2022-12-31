package com.ilhomsoliev.noteapp.domain.use_case

import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class InsertNoteLabelCrossRefUseCase @Inject constructor(
    private val repository: NoteRepository
){
    suspend operator fun invoke(
        noteLabelCrossRef: NoteLabelCrossRef
    ){
        repository.insertNoteLabelCrossRef(noteLabelCrossRef)
    }
}