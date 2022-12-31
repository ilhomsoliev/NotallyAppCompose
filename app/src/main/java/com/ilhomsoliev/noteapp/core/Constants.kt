package com.ilhomsoliev.noteapp.core

object Constants {
    // Navigation
    const val NOTE_ID_ARG = "note_id"

    val listOfHomeScreenBottomSheetOptionsUnpinned = listOf(
        "Pin",
        "Share",
        "Labels",
        "Export",
        "Delete",
        "Archive",
        "Change Color"
    )
    val listOfHomeScreenBottomSheetOptionsPinned = listOf(
        "Unpin",
        "Share",
        "Labels",
        "Export",
        "Delete",
        "Archive",
        "Change Color"
    )
    val listOfTrashScreenBottomSheetOptions = listOf(
        "Restore",
        "Delete Forever",
    )
    val listOfArchiveScreenBottomSheetOptions = listOf(
        "Unarchive",
    )
    val listOfLabelScreenBottomSheetOptions = listOf(
        "Edit",
        "Delete",
    )
}