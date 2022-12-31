package com.ilhomsoliev.noteapp.data.repository

import com.ilhomsoliev.noteapp.data.data_source.LabelDao
import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.repository.LabelRepository
import kotlinx.coroutines.flow.Flow

class LabelRepositoryImpl(
    private val dao: LabelDao
) : LabelRepository {
    override fun getLabels(): Flow<List<Label>> = dao.getLabels()

    override suspend fun insertLabel(label: Label) {
        dao.insert(label)
    }

    override suspend fun deleteLabel(label: Label) {
        dao.delete(label.labelId)
    }

}