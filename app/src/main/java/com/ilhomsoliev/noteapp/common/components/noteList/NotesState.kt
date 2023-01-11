package com.ilhomsoliev.noteapp.common.components.noteList

import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels

data class NotesState (
    val notes:List<NoteWithLabels> = emptyList(),
)