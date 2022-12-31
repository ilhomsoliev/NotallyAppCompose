package com.ilhomsoliev.noteapp.presentation.archive

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilhomsoliev.noteapp.common.components.BottomSheet
import com.ilhomsoliev.noteapp.common.components.noteList.NoteList
import com.ilhomsoliev.noteapp.core.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArchiveScreen(
    goToAddScreen: (Int) -> Unit,
    viewModel: ArchiveScreenViewModel = hiltViewModel()
) {
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
                    "Unarchive" -> {
                        viewModel.unarchiveSelectedNote()
                    }
                }
                coroutineScope.launch {
                    sheetState.hide()
                }
            }, listOfActions = Constants.listOfArchiveScreenBottomSheetOptions)
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