package com.ilhomsoliev.noteapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface Preferences {

    suspend fun putDarkThemeValue(value: Boolean)

    val darkTheme: Flow<Boolean>

    suspend fun putIsListViewValue(value: Boolean)

    val isListView: Flow<Boolean>

}