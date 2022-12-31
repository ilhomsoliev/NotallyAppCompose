package com.ilhomsoliev.noteapp.app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.noteapp.domain.repository.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val preferences: Preferences
):ViewModel() {
    val isDarkTheme = mutableStateOf(false)
    init {
        getTheme()
    }
    private fun getTheme(){
        preferences.darkTheme.onEach {
            isDarkTheme.value = it
        }.launchIn(viewModelScope)
    }
}