package com.ilhomsoliev.noteapp.common.components.noteList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListGridView(
    notesWithLabels: List<NoteWithLabels>,
    onLongClick: (NoteWithLabels, Boolean) -> Unit,
    onClick: (Int) -> Unit
) {
    if (notesWithLabels.filter { !it.note.pinned }.size != notesWithLabels.size) {
        Text(
            modifier = Modifier.padding(start = 24.dp),
            fontWeight = FontWeight.Medium,
            text = "Pinned",
            color = Color.Black
        )
    }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notesWithLabels.filter { it.note.pinned }) { note ->
                NoteItem(note, onClick = { note.note.noteId?.let { onClick(it) } },
                    onLongClick = {
                        onLongClick(note, true)
                    })
            }
        }

    if (notesWithLabels.filter { !it.note.pinned }.size != notesWithLabels.size && notesWithLabels.filter { !it.note.pinned }
            .isNotEmpty()) {
        Text(
            modifier = Modifier.padding(start = 24.dp),
            fontWeight = FontWeight.Medium,
            text = "Others"
        )
    }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notesWithLabels.filter { !it.note.pinned }) { note ->
                NoteItem(note, onClick = {
                    note.note.noteId?.let { onClick(it) }
                }, onLongClick = {
                    onLongClick(note, false)
                })
            }
        }

}