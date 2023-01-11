package com.ilhomsoliev.noteapp.common.components.noteList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import kotlinx.coroutines.launch

@Composable
fun NoteList(
    isListView: Boolean,
    notesWithLabels: List<NoteWithLabels>,
    onLongClick: (NoteWithLabels, Boolean) -> Unit,
    onClick: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
        if (isListView) {
            NoteListListView(
                notesWithLabels = notesWithLabels,
                onLongClick = onLongClick,
                onClick = onClick,
            )
        } else {
            NoteListGridView(
                notesWithLabels = notesWithLabels,
                onLongClick = onLongClick,
                onClick = onClick,
            )
        }
    }
}