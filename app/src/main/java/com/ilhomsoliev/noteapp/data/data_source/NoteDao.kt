package com.ilhomsoliev.noteapp.data.data_source

import androidx.room.*
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Transaction
    @Query("SELECT * FROM note")
    fun getNotesWithLabels():Flow<List<NoteWithLabels>>

    @Transaction
    @Query("SELECT * FROM note WHERE noteId = :id")
    suspend fun getNoteWithLabelsById(id :Int):NoteWithLabels

    @Query("SELECT * FROM note WHERE noteId = :id")
    suspend fun getNoteById(id:Int):Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note):Long

    @Delete
    suspend fun deleteNote(note:Note)

    @Delete
    suspend fun deleteNoteLabelCrossRef(crossRef:NoteLabelCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteLabelCrossRef(crossRef: NoteLabelCrossRef)


}