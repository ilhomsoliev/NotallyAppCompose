package com.ilhomsoliev.noteapp.presentation.home

import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels

data class NotesState (
    val notes:List<NoteWithLabels> = emptyList(),
    )