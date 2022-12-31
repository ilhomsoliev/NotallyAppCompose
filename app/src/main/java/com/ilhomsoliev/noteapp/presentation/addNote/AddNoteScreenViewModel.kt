package com.ilhomsoliev.noteapp.presentation.addNote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.use_case.InsertNoteLabelCrossRefUseCase
import com.ilhomsoliev.noteapp.domain.use_case.label.GetLabelsUseCase
import com.ilhomsoliev.noteapp.domain.use_case.note.DeleteNoteWithLabelsUseCase
import com.ilhomsoliev.noteapp.domain.use_case.note.GetNoteWithLabelsByIdUseCase
import com.ilhomsoliev.noteapp.domain.use_case.note.InsertNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteScreenViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getNoteWithLabelsByIdUseCase: GetNoteWithLabelsByIdUseCase,
    private val getLabelsUseCase: GetLabelsUseCase,
    private val insertNoteLabelCrossRefUseCase: InsertNoteLabelCrossRefUseCase,
    private val deleteNoteWithLabelsUseCase: DeleteNoteWithLabelsUseCase
) : ViewModel() {

    private val _note = mutableStateOf(Note(timestamp = System.currentTimeMillis()))
    val note: State<Note> = _note

     var _selectedLabels = mutableStateListOf<Label>()

    private val _labels = mutableStateOf<List<Label>>(emptyList())
    val labels: State<List<Label>> = _labels

    init {
        getLabels()
    }

    fun selectLabel(label: String) {
        _selectedLabels.add(Label(label))
    }

    fun deleteLabelFromList(label: String) {
        _selectedLabels.remove(Label(label))
    }

    fun onTitleChange(value: String) {
        _note.value = note.value.copy(
            title = value
        )
    }

    fun onDescriptionChange(value: String) {
        _note.value = note.value.copy(
            content = value
        )
    }

    fun addNote() {
        if (_note.value.content.isNotEmpty()) {
            viewModelScope.launch {
                insertNoteUseCase(NoteWithLabels(_note.value, _selectedLabels.toMutableList()))
            }
        }
    }

    suspend fun insertNoteLabelCrossRef(noteId: Int, labelId: String) {
        viewModelScope.launch {
            insertNoteLabelCrossRefUseCase(NoteLabelCrossRef(noteId, labelId))
        }
    }

    fun changePinned() {
        _note.value = note.value.copy(pinned = !note.value.pinned)
    }

    fun getNoteById(noteId: Int) {
        viewModelScope.launch {
            val noteWithLabels = getNoteWithLabelsByIdUseCase(noteId)
            _note.value = noteWithLabels.note
            noteWithLabels.labels.forEach {
                _selectedLabels.add(it)
            }
        }
    }

    private fun getLabels() {
        getLabelsUseCase().onEach {
            _labels.value = it
        }.launchIn(viewModelScope)
    }

    fun deleteCurrentNote() {
        viewModelScope.launch {
            deleteNoteWithLabelsUseCase(NoteWithLabels(_note.value))
        }
    }

}