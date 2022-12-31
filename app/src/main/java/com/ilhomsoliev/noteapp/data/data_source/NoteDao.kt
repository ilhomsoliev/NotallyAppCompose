package com.ilhomsoliev.noteapp.data.data_source

import androidx.room.*
import com.ilhomsoliev.noteapp.domain.model.Note
import com.ilhomsoliev.noteapp.domain.model.relations.NoteLabelCrossRef
import com.ilhomsoliev.noteapp.domain.model.relations.NoteWithLabels
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Transaction
    @Query("SELECT * FROM note WHERE isDeleted IS 0 AND isArchived IS 0")
    fun getNotesWithLabels():Flow<List<NoteWithLabels>>

    @Transaction
    @Query("SELECT * FROM note WHERE isDeleted IS 1")
    fun getDeletedNotesWithLabels():Flow<List<NoteWithLabels>>

    @Transaction
    @Query("SELECT * FROM note WHERE isDeleted IS 0 AND isArchived IS 1")
    fun getArchivedNotesWithLabels():Flow<List<NoteWithLabels>>

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