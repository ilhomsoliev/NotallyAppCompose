package com.ilhomsoliev.noteapp.domain.use_case.label

import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.repository.LabelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLabelsUseCase @Inject constructor(
    private val repository: LabelRepository
){
    operator fun invoke(): Flow<List<Label>> {
        return repository.getLabels().map { labels ->
            labels.sortedBy { it.labelId }
        }
    }
}