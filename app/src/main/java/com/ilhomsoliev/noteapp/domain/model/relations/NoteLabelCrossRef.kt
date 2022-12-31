package com.ilhomsoliev.noteapp.domain.model.relations
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.model.Note

@Entity(
    primaryKeys = ["noteId", "labelId"],
    foreignKeys = [
        ForeignKey(
            entity = Note::class,
            parentColumns = ["noteId"],
            childColumns = ["noteId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),
        ForeignKey(
            entity = Label::class,
            parentColumns = ["labelId"],
            childColumns = ["labelId"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ])
data class NoteLabelCrossRef(
    val noteId: Int,
    val labelId: String
)