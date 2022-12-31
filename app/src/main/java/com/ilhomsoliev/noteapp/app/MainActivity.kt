package com.ilhomsoliev.noteapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import com.ilhomsoliev.noteapp.app.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel:MainActivityViewModel by viewModels()
        setContent {
            val isDarkTheme by  viewModel.isDarkTheme
            NoteAppTheme(darkTheme = isDarkTheme) {
                Navigation()
            }
        }
    }
}