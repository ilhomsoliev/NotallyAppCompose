package com.ilhomsoliev.noteapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.preferencesDataStore
import com.ilhomsoliev.noteapp.domain.repository.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val PREFERENCES_NAME = "preferences_name"

private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)
val THEME = booleanPreferencesKey("THEME")
val LIST_VIEW = booleanPreferencesKey("LIST_VIEW")

class PreferencesImpl @Inject constructor(
    private val context: Context,
    override val darkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[THEME] ?: false
        },
    override val isListView: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[LIST_VIEW] ?: false
    }
) : Preferences {

    override suspend fun putDarkThemeValue(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME] = value
        }
    }

    override suspend fun putIsListViewValue(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[LIST_VIEW] = value
        }
    }

}