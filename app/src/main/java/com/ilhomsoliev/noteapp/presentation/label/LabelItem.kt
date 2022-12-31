package com.ilhomsoliev.noteapp.presentation.label

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LabelItem(labelId: String, onClick: () -> Unit, onLongClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().combinedClickable(
        onLongClick = {
            onLongClick()
        },
        onClick = {
            onClick()

        }
    )) {
        Text(modifier = Modifier.padding(16.dp),fontSize = 16.sp, text = labelId)
        Divider()
    }

}