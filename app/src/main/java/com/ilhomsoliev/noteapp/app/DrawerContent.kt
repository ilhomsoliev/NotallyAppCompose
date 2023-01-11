package com.ilhomsoliev.noteapp.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.noteapp.core.Screens

@Composable
fun DrawerContent(
    goToNotesScreen: () -> Unit,
    goToLabelsScreen: () -> Unit,
    goToTrashScreen: () -> Unit,
    goToArchiveScreen: () -> Unit,
    goToSettingsScreen: () -> Unit,
    currentScreen: String,
) {

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Note App",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
        DrawerItem(
            "Notes",
            icon = Icons.Default.Article,
            onClick = goToNotesScreen,
            isActive = currentScreen == Screens.HomeScreen.route
        )
        Divider()
        DrawerItem(
            "Labels",
            icon = Icons.Default.Label,
            onClick = goToLabelsScreen,
            isActive = currentScreen == Screens.LabelScreen.route
        )
        Divider()
        DrawerItem(
            "Trash",
            icon = Icons.Default.Delete,
            onClick = goToTrashScreen,
            isActive = currentScreen == Screens.TrashScreen.route
        )
        DrawerItem(
            "Archive",
            icon = Icons.Default.Archive,
            onClick = goToArchiveScreen,
            isActive = currentScreen == Screens.ArchiveScreen.route
        )
        Divider()
        DrawerItem(
            "Settings",
            icon = Icons.Default.Settings,
            onClick = goToSettingsScreen,
            isActive = currentScreen == Screens.SettingsScreen.route
        )
    }
}

@Composable
fun DrawerItem(text: String, icon: ImageVector, isActive: Boolean, onClick: () -> Unit) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClick()
            }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(if (isActive) MaterialTheme.colors.primary else Color.Transparent)
            ) {
                Row( modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        tint = if (isActive) MaterialTheme.colors.onPrimary else Color.Gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = text, color = if (isActive) MaterialTheme.colors.onPrimary else Color.Gray)
                }
            }
        }

}