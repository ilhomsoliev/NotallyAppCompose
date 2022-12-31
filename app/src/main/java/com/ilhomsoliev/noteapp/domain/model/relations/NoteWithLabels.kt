package com.ilhomsoliev.noteapp.domain.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.model.Note

data class NoteWithLabels(
    @Embedded val note: Note = Note(),
    @Relation(
        parentColumn = "noteId",
        entityColumn = "labelId",
        associateBy = Junction(NoteLabelCrossRef::class)
    )
    var labels:MutableList<Label> = mutableListOf()
)