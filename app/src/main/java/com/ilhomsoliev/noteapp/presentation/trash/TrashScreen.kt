package com.ilhomsoliev.noteapp.presentation.trash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilhomsoliev.noteapp.common.components.BottomSheet
import com.ilhomsoliev.noteapp.common.components.noteList.NoteList
import com.ilhomsoliev.noteapp.core.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrashScreen(goToAddScreen: (Int) -> Unit, viewModel:TrashScreenViewModel = hiltViewModel()) {
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
            BottomSheet(onItemClick = {
                when (it) {
                    "Restore" -> {
                        viewModel.restoreSelectedNote()
                    }
                    "Delete Forever" -> {
                        viewModel.deleteForeverNote()
                    }
                }
                coroutineScope.launch {
                    sheetState.hide()
                }
            }, listOfActions = Constants.listOfTrashScreenBottomSheetOptions)
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
    }
}