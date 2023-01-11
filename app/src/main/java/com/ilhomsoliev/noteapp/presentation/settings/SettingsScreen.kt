package com.ilhomsoliev.noteapp.presentation.settings

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ilhomsoliev.noteapp.common.components.CustomDialogPickList

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    var sliderPosition by remember { mutableStateOf(0f) }
    var isViewDialogActive by remember { mutableStateOf(false) }
    var isThemeDialogActive by remember { mutableStateOf(false) }
    val isDarkTheme by viewModel.isDarkTheme
    val isListViewTheme by viewModel.isListViewTheme

    if (isViewDialogActive) {
        CustomDialogPickList(
            title = "View",
            list = listOf("List", "Grid"),
            selectedItem = if (isListViewTheme) 0 else 1,
            onItemClick = { listView ->
                if (listView == "List") {
                    viewModel.setListView(true)
                } else {
                    viewModel.setListView(false)
                }
                isViewDialogActive = false

            }
        ) {
            isViewDialogActive = false
        }
    }
    if (isThemeDialogActive) {
        CustomDialogPickList(title = "Theme", list = listOf("Light", "Dark"),
            selectedItem = if (isDarkTheme) 1 else 0, onItemClick = {
                when (it) {
                    "Light" -> {
                        viewModel.setTheme(false)
                    }
                    "Dark" -> {
                        viewModel.setTheme(true)
                    }
                }
                isThemeDialogActive = false

            }) {
            isThemeDialogActive = false
        }
    }
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Appearance",
            fontSize = 14.sp
        )
        AppearanceItem(title = "View", picked =if (isListViewTheme) "List" else "Grid" ) {
            isViewDialogActive = true
        }
        AppearanceItem(title = "Theme", picked = if (isDarkTheme) "Dark" else "Light") {
            isThemeDialogActive = true
        }
        AppearanceItem(title = "Date Format", picked = "1 day ago") {

        }
        Divider()
        Text(modifier = Modifier.padding(12.dp), text = "Content density")
        Text(modifier = Modifier.padding(12.dp), text = "Max items to display in")
        Slider(steps = 1, value = sliderPosition, onValueChange = {
            sliderPosition = it
        })
        Divider()
        Text(modifier = Modifier.padding(12.dp), text = "Backup")
        BackupItem(title = "Import backup") {

        }
        BackupItem(title = "Export backup") {

        }
        Divider()
        Text(modifier = Modifier.padding(12.dp), text = "About")
        BackupItem(title = "Github") {

        }
        BackupItem(title = "Libraries") {

        }
        BackupItem(title = "Rate this app") {

        }
    }
}

@Composable
fun AppearanceItem(
    title: String,
    picked: String,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = title)
            Text(text = picked, color = Color.Gray)

        }
    }
}

@Composable
fun BackupItem(
    title: String,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(text = title)
        }
    }
}