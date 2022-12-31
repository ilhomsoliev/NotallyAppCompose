package com.ilhomsoliev.noteapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.ilhomsoliev.noteapp.common.components.BottomSheet
import com.ilhomsoliev.noteapp.common.components.noteList.NoteList
import com.ilhomsoliev.noteapp.core.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(goToAddScreen: (Int) -> Unit, viewModel: HomeScreenViewModel = hiltViewModel()) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    val notesState by remember { viewModel.notesState }
    val isSelectedNotePinned by remember { viewModel.isSelectedNotePinned }
    val isListView by viewModel.isListViewTheme
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheet(
                onItemClick = {
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
                        "Archive" -> {
                            viewModel.archiveSelectedNote()
                        }
                    }
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                },
                if (isSelectedNotePinned) Constants.listOfHomeScreenBottomSheetOptionsPinned else Constants.listOfHomeScreenBottomSheetOptionsUnpinned
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        NoteList(
            isListView = isListView,
            notesWithLabels = notesState.notes,
            onClick = {
                goToAddScreen(it)
            },
            onLongClick = { note, isPinned ->
                viewModel.setSelectedNote(note)
                viewModel.setIsSelectedNotePinned(isPinned)
                coroutineScope.launch {
                    if (sheetState.isVisible) sheetState.hide()
                    else sheetState.show()
                }
            }
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(modifier = Modifier.padding(8.dp), onClick = {
                goToAddScreen(-1)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)
            }
        }
    }
}
