package com.ilhomsoliev.noteapp.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ilhomsoliev.noteapp.domain.model.Label
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.LabelWithNotes
import kotlinx.coroutines.flow.Flow

@Dao
interface LabelDao {

    @Insert
    suspend fun insert(label: Label)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(labels: List<Label>)

    @Query("DELETE FROM Label WHERE labelId = :value")
    suspend fun delete(value: String)

    @Query("UPDATE Label SET labelId = :newValue WHERE labelId = :oldValue")
    suspend fun update(oldValue: String, newValue: String)

    @Query("SELECT * FROM label")
    fun getLabels(): Flow<List<Label>>

    @Transaction
    @Query("SELECT * FROM label")
    fun getLabelsWithNotes():List<LabelWithNotes>

}