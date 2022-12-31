package com.ilhomsoliev.noteapp.domain.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.model.Note

data class LabelWithNotes(
    @Embedded val label: Label,
    @Relation(
        parentColumn = "labelId",
        entityColumn = "noteId",
        associateBy = Junction(NoteLabelCrossRef::class)
    )
    val notes:List<Note>
)