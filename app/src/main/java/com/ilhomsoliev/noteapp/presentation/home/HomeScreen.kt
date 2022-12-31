package com.ilhomsoliev.noteapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(goToAddScreen: (Int) -> Unit, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    val notes by remember { viewModel.notes }
    val isSelectedNotePinned by remember { viewModel.isSelectedNotePinned }
    val isListView by viewModel.isListViewTheme
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheet(isPinned = isSelectedNotePinned, onItemClick = {
                when (it) {
                    "Pin" -> {
                        viewModel.pinSelectedNote()
                    }
                    "Unpin" -> {
                        viewModel.unpinSelectedNote()
                    }
                    "Share" -> {

                    }
                    "Delete" -> {
                        viewModel.deleteSelectedNote()
                    }
                }
                coroutineScope.launch {
                    sheetState.hide()
                }
            })
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (isListView) {
                LazyColumn {
                    item {
                        if (notes.notes.filter { !it.note.pinned }.size != notes.notes.size) {
                            Text(
                                modifier = Modifier.padding(start = 24.dp),
                                fontWeight = FontWeight.Medium,
                                text = "Pinned",
                                color = Color.Black
                            )
                        }
                    }
                    items(notes.notes.filter { it.note.pinned }) { note ->

                        NoteItem(note, onClick = {
                            note.note.noteId?.let { goToAddScreen(it) }
                        }, onLongClick = {
                            viewModel.setSelectedNote(note)
                            viewModel.setIsSelectedNotePinned(true)
                            coroutineScope.launch {
                                if (sheetState.isVisible) sheetState.hide()
                                else sheetState.show()
                            }
                        })
                    }
                    item {
                        if (notes.notes.filter { !it.note.pinned }.size != notes.notes.size && notes.notes.filter { !it.note.pinned }
                                .isNotEmpty()) {
                            Text(
                                modifier = Modifier.padding(start = 24.dp),
                                fontWeight = FontWeight.Medium,
                                text = "Others"
                            )
                        }
                    }
                    items(notes.notes.filter { !it.note.pinned }) { note ->

                        NoteItem(note, onClick = {
                            note.note.noteId?.let { goToAddScreen(it) }
                        }, onLongClick = {
                            viewModel.setSelectedNote(note)
                            viewModel.setIsSelectedNotePinned(false)
                            coroutineScope.launch {
                                if (sheetState.isVisible) {
                                    sheetState.hide()
                                } else {
                                    sheetState.show()
                                }
                            }
                        })
                    }
                }
            } else {
                if (notes.notes.filter { !it.note.pinned }.size != notes.notes.size) {
                    Text(
                        modifier = Modifier.padding(start = 24.dp),
                        fontWeight = FontWeight.Medium,
                        text = "Pinned",
                        color = Color.Black
                    )
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(notes.notes.filter { it.note.pinned }) { note ->
                            NoteItem(note, onClick = {
                                note.note.noteId?.let { goToAddScreen(it) }
                            }, onLongClick = {
                                viewModel.setSelectedNote(note)
                                viewModel.setIsSelectedNotePinned(false)
                                coroutineScope.launch {
                                    if (sheetState.isVisible) {
                                        sheetState.hide()
                                    } else {
                                        sheetState.show()
                                    }
                                }
                            })
                        }
                    }
                }
                if (notes.notes.filter { !it.note.pinned }.size != notes.notes.size && notes.notes.filter { !it.note.pinned }
                        .isNotEmpty()) {
                    Text(
                        modifier = Modifier.padding(start = 24.dp),
                        fontWeight = FontWeight.Medium,
                        text = "Others"
                    )
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(notes.notes.filter { it.note.pinned }) { note ->
                            NoteItem(note, onClick = {
                                note.note.noteId?.let { goToAddScreen(it) }
                            }, onLongClick = {
                                viewModel.setSelectedNote(note)
                                viewModel.setIsSelectedNotePinned(false)
                                coroutineScope.launch {
                                    if (sheetState.isVisible) {
                                        sheetState.hide()
                                    } else {
                                        sheetState.show()
                                    }
                                }
                            })
                        }
                    }
                }

            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(modifier = Modifier.padding(8.dp), onClick = {
                goToAddScreen(-1)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)
            }
        }
    }
}

@Composable
fun BottomSheet(onItemClick: (String) -> Unit, isPinned: Boolean) {
    val listOfActions = listOf(
        if (!isPinned) "Pin" else "Unpin",
        "Share",
        "Labels",
        "Export",
        "Delete",
        "Archive",
        "Change Color"
    )
    LazyColumn() {
        items(listOfActions) { text ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(text)
                }) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
