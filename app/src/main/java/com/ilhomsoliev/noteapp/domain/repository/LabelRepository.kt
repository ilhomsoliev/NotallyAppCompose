package com.ilhomsoliev.noteapp.domain.repository

import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import kotlinx.coroutines.flow.Flow

interface LabelRepository {
    fun getLabels(): Flow<List<Label>>
    suspend fun insertLabel(label: Label)
    suspend fun deleteLabel(label: Label)
}