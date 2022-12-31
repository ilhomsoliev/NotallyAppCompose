package com.ilhomsoliev.noteapp.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialogPickList(
    title: String,
    list: List<String>,
    selectedItem:Int,
    onItemClick:(String)->Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colors.background
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.padding(start = 24.dp, top = 16.dp), text = title, fontWeight = FontWeight.Medium)
                Column {
                    list.forEach { text ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == list[selectedItem]),
                                onClick = {
                                    onItemClick(text)
                                }
                            )
                            .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (text == list[selectedItem]),
                                onClick = {
                                    onItemClick(text)
                                }
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.body1.merge(),
                                modifier = Modifier.padding(start = 8.dp)
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
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            onDismissRequest()
                        }) {
                        Text(text = "Cancel", modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }

}