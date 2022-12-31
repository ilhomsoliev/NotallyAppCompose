package com.ilhomsoliev.noteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Label(@PrimaryKey(autoGenerate = false) val labelId: String)