package com.ilhomsoliev.noteapp.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BottomSheet(onItemClick: (String) -> Unit, listOfActions: List<String>) {
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
