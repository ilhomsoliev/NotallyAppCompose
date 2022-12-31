package com.ilhomsoliev.noteapp.presentation.addNote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilhomsoliev.noteapp.app.BaseNoteModel
import com.ilhomsoliev.noteapp.common.components.CustomDialogPickListCheckBox
import com.ilhomsoliev.noteapp.domain.model.Label
import java.util.*

@Composable
fun AddNoteScreen(
    noteId: Int,
    goToHomeScreen: () -> Unit,
    viewModel: AddNoteScreenViewModel = hiltViewModel()
) {

    val note by remember {
        viewModel.note
    }
    val notelabels = remember { viewModel._selectedLabels }

    LaunchedEffect(true) {
        if (noteId != -1) viewModel.getNoteById(noteId)
    }

    var showPopup by remember { mutableStateOf(false) }

    var isLabelDialogActive by remember { mutableStateOf(false) }

    val labels by viewModel.labels

    if (isLabelDialogActive) {
        CustomDialogPickListCheckBox(
            title = "Labels",
            list = labels.map { it.labelId },
            selectedItems = notelabels.map { it.labelId },
            onItemClick = {
                if (notelabels.contains(Label(it))) {
                    viewModel.deleteLabelFromList(it)
                } else {
                    viewModel.selectLabel(it)
                }
            },
            onSaveClick = {
                isLabelDialogActive = false
            }) {
            isLabelDialogActive = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        TopAppBar(
            elevation = 8.dp,
            navigationIcon = {
                IconButton(onClick = {
                    viewModel.addNote()
                    goToHomeScreen()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }, actions = {
                IconButton(onClick = {
                    viewModel.changePinned()
                }) {
                    Icon(
                        imageVector = if (note.pinned) Icons.Filled.PushPin else Icons.Outlined.PushPin,
                        contentDescription = ""
                    )
                }
                Box {
                    IconButton(onClick = {
                        showPopup = !showPopup
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                    }

                    DropdownMenu(
                        offset = DpOffset(0.dp, 0.dp),
                        expanded = showPopup,
                        onDismissRequest = { showPopup = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            isLabelDialogActive = true
                        }) {
                            Text("Labels")
                        }
                        DropdownMenuItem(onClick = {
                            goToHomeScreen()
                            viewModel.deleteCurrentNote()
                        }) {
                            Text("Delete")
                        }
                        DropdownMenuItem(onClick = { /* Handle send feedback! */ }) {
                            Text("Archive")
                        }
                    }
                }

            }, title = {})
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = note.title,
            placeholder = { Text(text = "Title") },
            onValueChange = { viewModel.onTitleChange(it) },
            textStyle = TextStyle.Default.copy(fontSize = 20.sp, fontWeight = FontWeight.Black),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            text = BaseNoteModel.getDateFormatter(LocalContext.current)
                .format(note.timestamp)
        )
        Divider()
        TextField(
            modifier = Modifier.fillMaxSize(),
            value = note.content,
            placeholder = { Text(text = "Description") },
            onValueChange = { viewModel.onDescriptionChange(it) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}