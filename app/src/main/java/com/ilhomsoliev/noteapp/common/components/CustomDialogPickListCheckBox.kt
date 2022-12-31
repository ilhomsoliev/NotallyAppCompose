package com.ilhomsoliev.noteapp.common.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialogPickListCheckBox(
    title: String,
    list: List<String>,
    selectedItems: List<String>,
    onItemClick: (String) -> Unit,
    onSaveClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Log.d("Hello","$selectedItems mmmmmm $list")
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 24.dp, top = 16.dp),
                    text = title,
                    fontWeight = FontWeight.Medium
                )
                Column {
                    list.forEach { text ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedItems.contains(text),
                                onClick = {
                                    onItemClick(text)
                                }
                            )
                            .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = selectedItems.contains(text),
                                onCheckedChange = {
                                    onItemClick(text)
                                },
                                enabled = true
                            )
                            Text(
                                modifier = Modifier.padding(start = 8.dp), text = text
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Row() {
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable {
                                onDismissRequest()
                            }) {
                            Text(text = "Cancel", modifier = Modifier.padding(8.dp))
                        }
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .clickable {
                                onSaveClick()
                            }) {
                            Text(text = "Save", modifier = Modifier.padding(8.dp))
                        }
                    }

                }
            }
        }
    }
}