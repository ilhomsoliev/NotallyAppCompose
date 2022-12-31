package com.ilhomsoliev.noteapp.common.components.noteList

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun NoteItem(note: NoteWithLabels, onClick: () -> Unit, onLongClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .combinedClickable(
            onLongClick = onLongClick,
            onClick = onClick
        ), border = BorderStroke(1.dp, Color.Gray)) {
        Column {
            if (note.note.title.isNotEmpty()) {
                Text(modifier = Modifier.padding(8.dp), text = note.note.title)
            }
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = PrettyTime().format(Date(note.note.timestamp)),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(modifier = Modifier.padding(8.dp), text = note.note.content)
            FlowRow(
                modifier = Modifier.padding(8.dp),
                mainAxisAlignment = MainAxisAlignment.Start,
                mainAxisSize = SizeMode.Expand,
                crossAxisSpacing = 12.dp,
                mainAxisSpacing = 8.dp
            ) {
                note.labels.forEach {
                    LabelUnderNoteItem(text = it.labelId)
                }
            }
        }
    }
}

@Composable
fun LabelUnderNoteItem(text: String) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Gray,RoundedCornerShape(size = 12.dp))
    ) {
        Text(modifier = Modifier.padding(4.dp), text = text)
    }
}