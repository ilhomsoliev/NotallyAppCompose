package com.ilhomsoliev.noteapp.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomToolbar(
    title:String,
    onBackClick:()->Unit,
    isBackClickEnabled:Boolean
    ) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            if (isBackClickEnabled) {
                IconButton(onClick = {
                    onBackClick()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                }
            }
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Divider(modifier = Modifier.fillMaxWidth().width(1.dp))
    }
}