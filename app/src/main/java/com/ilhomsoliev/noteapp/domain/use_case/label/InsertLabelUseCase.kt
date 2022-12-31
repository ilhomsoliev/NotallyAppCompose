package com.ilhomsoliev.noteapp.domain.use_case.label

import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.repository.LabelRepository
import com.ilhomsoliev.noteapp.domain.repository.NoteRepository
import javax.inject.Inject

class InsertLabelUseCase @Inject constructor(
    private val repository: LabelRepository

){
    suspend operator fun invoke(
        label: Label
    ){
        repository.insertLabel(label)
    }
}