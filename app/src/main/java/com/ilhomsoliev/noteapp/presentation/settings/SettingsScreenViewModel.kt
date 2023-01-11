package com.ilhomsoliev.noteapp.presentation.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.noteapp.domain.repository.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val preferences: Preferences
):ViewModel() {
    val isDarkTheme = mutableStateOf(false)
    val isListViewTheme = mutableStateOf(false)
    init {
        getTheme()
        getListView()
    }
    private fun getTheme(){
        preferences.darkTheme.onEach {
            isDarkTheme.value = it
        }.launchIn(viewModelScope)
    }


    fun setTheme(value:Boolean){
        viewModelScope.launch {
            preferences.putDarkThemeValue(value)
        }
    }

    private fun getListView(){
        preferences.isListView.onEach {
            isListViewTheme.value = it
        }.launchIn(viewModelScope)
    }

    fun setListView(value:Boolean){
        viewModelScope.launch {
            preferences.putIsListViewValue(value)
        }
    }
}