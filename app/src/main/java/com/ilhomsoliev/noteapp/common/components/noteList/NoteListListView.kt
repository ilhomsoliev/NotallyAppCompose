package com.ilhomsoliev.noteapp.common.components.noteList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels

@Composable
fun NoteListListView(
    notesWithLabels: List<NoteWithLabels>,
    onLongClick: (NoteWithLabels, Boolean) -> Unit,
    onClick: (Int) -> Unit
    ) {
    LazyColumn {
        item {
            if (notesWithLabels.filter { !it.note.pinned }.size != notesWithLabels.size) {
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    fontWeight = FontWeight.Medium,
                    text = "Pinned",
                    color = Color.Black
                )
            }
        }
        items(notesWithLabels.filter { it.note.pinned }) { note: NoteWithLabels ->
            NoteItem(
                note,
                onClick = { note.note.noteId?.let { onClick(it) } },
                onLongClick = { onLongClick(note, true) }
            )
        }
        item {
            if (notesWithLabels.filter { !it.note.pinned }.size != notesWithLabels.size && notesWithLabels.filter { !it.note.pinned }
                    .isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(start = 24.dp),
                    fontWeight = FontWeight.Medium,
                    text = "Others"
                )
            }
        }
        items(notesWithLabels.filter { !it.note.pinned }) { note: NoteWithLabels ->
            NoteItem(
                note, onClick = { note.note.noteId?.let { onClick(it) } },
                onLongClick = { onLongClick(note, false) }
            )
        }
    }
}