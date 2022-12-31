package com.ilhomsoliev.noteapp.presentation.trash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.Preferences
import com.ilhomsoliev.noteapp.domain.use_case.note.*
import com.ilhomsoliev.noteapp.common.components.noteList.NotesState
import com.ilhomsoliev.noteapp.domain.use_case.note.DeleteForeverNoteUseCase
import com.ilhomsoliev.noteapp.domain.use_case.note.get.GetDeletedNotesWithLabelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TrashScreenViewModel @Inject constructor(
    private val deleteForeverNoteUseCase: DeleteForeverNoteUseCase,
    private val restoreNoteUseCase: RestoreNoteUseCase,
    private val getDeletedNotesWithLabelsUseCase: GetDeletedNotesWithLabelsUseCase,
    private val preferences: Preferences
) : ViewModel() {
    private val _notesState = mutableStateOf(NotesState())
    val notesState: State<NotesState> = _notesState

    private val _selectedNote =
        mutableStateOf(NoteWithLabels(Note(timestamp = System.currentTimeMillis())))
    val selectedNote: State<NoteWithLabels> = _selectedNote

    private val _isSelectedNotePinned = mutableStateOf(false)
    val isSelectedNotePinned: State<Boolean> = _isSelectedNotePinned

    val isListViewTheme = mutableStateOf(false)

    private var getNotesJob: Job? = null

    init {
        //getNotes()
        getNotesWithLabels()
        getListView()
    }

    private fun getListView() {
        preferences.isListView.onEach {
            isListViewTheme.value = it
        }.launchIn(viewModelScope)
    }

    private fun getNotesWithLabels() {
        getNotesJob?.cancel()
        getNotesJob = getDeletedNotesWithLabelsUseCase().onEach {
            _notesState.value = _notesState.value.copy(notes = it)
        }.launchIn(viewModelScope)
    }

    fun restoreSelectedNote() {
        viewModelScope.launch {
            restoreNoteUseCase(_selectedNote.value)
        }
    }

    fun deleteForeverNote() {
        viewModelScope.launch {
            deleteForeverNoteUseCase(_selectedNote.value)
        }
    }
    fun setSelectedNote(note: NoteWithLabels) {
        _selectedNote.value = note
    }

    fun setIsSelectedNotePinned(expression: Boolean) {
        _isSelectedNotePinned.value = expression
    }
}