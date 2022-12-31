package com.ilhomsoliev.noteapp.presentation.label

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.use_case.label.GetLabelsUseCase
import com.ilhomsoliev.noteapp.domain.use_case.label.InsertLabelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LabelScreenViewModel @Inject constructor(
    private val insertLabelUseCase: InsertLabelUseCase,
    private val getLabelsUseCase: GetLabelsUseCase
) : ViewModel() {

    private val _newLabel = mutableStateOf("")
    val newLabel: State<String> = _newLabel

    private val _labels = mutableStateOf<List<Label>>(emptyList())
    val labels: State<List<Label>> = _labels

    init {
        getLabels()
    }

    fun changeLabel(value: String) {
        _newLabel.value = value
    }

    fun addLabel() {
        if (newLabel.value.isNotEmpty()) {
            viewModelScope.launch {
                insertLabelUseCase(Label(labelId = _newLabel.value))
            }
        }
    }

    private fun getLabels() {
        getLabelsUseCase().onEach {
            _labels.value = it
        }.launchIn(viewModelScope)
    }
}