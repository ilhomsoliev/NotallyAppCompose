package com.ilhomsoliev.noteapp.presentation.label

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilhomsoliev.noteapp.common.components.CustomDialogTextField

@Composable
fun LabelScreen(isCreateLabelDialogActive: Boolean, onDismissRequest:()->Unit, viewModel: LabelScreenViewModel = hiltViewModel()) {
    val newLabel by viewModel.newLabel
    val labels by viewModel.labels

    if(isCreateLabelDialogActive){
        CustomDialogTextField(
            title = "Create Label",
            value = newLabel,
            onValueChange = viewModel::changeLabel,
            onSaveClick = {
                viewModel.addLabel()
                onDismissRequest()
            },
            onDismissRequest = onDismissRequest
        )
    }
    Column {
        LazyColumn(){
            items(labels){
                LabelItem(it.labelId, onClick = {

                }, onLongClick = {

                })
            }
        }
    }
}