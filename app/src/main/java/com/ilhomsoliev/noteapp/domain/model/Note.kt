package com.ilhomsoliev.noteapp.domain.model

import androidx.annotation.NonNull
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    val title:String = "",
    val content:String= "",
    val timestamp:Long = 0,
    val color:Int = 0,
    val pinned:Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val noteId:Int?=null
){
    companion object{
        val noteColors = listOf(Color.Red, Color.Blue)
    }
}