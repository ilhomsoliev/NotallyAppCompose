package com.ilhomsoliev.noteapp.core

sealed class Screens(val route:String) {
    object HomeScreen: Screens("HomeScreen")
    object LabelScreen: Screens("LabelScreen")
    object TrashScreen: Screens("TrashScreen")
    object ArchiveScreen: Screens("ArchiveScreen")
    object SettingsScreen: Screens("SettingsScreen")
    object AddEditNoteScreen: Screens("AddEditNoteScreen/{${Constants.NOTE_ID_ARG}}")

}