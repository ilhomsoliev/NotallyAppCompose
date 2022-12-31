package com.ilhomsoliev.noteapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import com.ilhomsoliev.noteapp.domain.repository.Preferences
import com.ilhomsoliev.noteapp.domain.use_case.InsertNoteLabelCrossRefUseCase
import com.ilhomsoliev.noteapp.domain.use_case.note.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteWithLabelsUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getNotesWithLabelsUseCase: GetNotesWithLabelsUseCase,
    private val preferences: Preferences
) : ViewModel() {
    private val _notes = mutableStateOf(NotesState())
    val notes: State<NotesState> = _notes

    private val _selectedNote = mutableStateOf(NoteWithLabels(Note(timestamp = System.currentTimeMillis())))
    val selectedNote:State<NoteWithLabels> = _selectedNote

    private val _isSelectedNotePinned = mutableStateOf(false)
    val isSelectedNotePinned:State<Boolean> = _isSelectedNotePinned

    val isListViewTheme = mutableStateOf(false)

    private var getNotesJob: Job? = null

    init {
        //getNotes()
        getNotesWithLabels()
        getListView()
    }
    private fun getListView(){
        preferences.isListView.onEach {
            isListViewTheme.value = it
        }.launchIn(viewModelScope)
    }
    private fun getNotesWithLabels() {
        getNotesJob?.cancel()
        getNotesJob = getNotesWithLabelsUseCase().onEach {
            _notes.value = _notes.value.copy(notes = it)
        }.launchIn(viewModelScope)
    }

    fun deleteSelectedNote(){
        viewModelScope.launch {
            deleteNoteUseCase(_selectedNote.value)
        }
    }

    fun setSelectedNote(note: NoteWithLabels) {
        _selectedNote.value = note
    }

    fun setIsSelectedNotePinned(expression: Boolean) {
        _isSelectedNotePinned.value = expression
    }

    fun pinSelectedNote() {
        viewModelScope.launch {
            _selectedNote.value.copy(note = _selectedNote.value.note.copy(pinned = true))
            insertNoteUseCase(_selectedNote.value.copy(note = _selectedNote.value.note.copy(pinned = true)))
        }
    }
    fun unpinSelectedNote() {
        viewModelScope.launch {
            insertNoteUseCase(_selectedNote.value.copy(note = _selectedNote.value.note.copy(pinned = false)))
        }
    }
}