package dev.goobar.advancedandroiddemo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.goobar.advancedandroiddemo.data.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteentity")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM noteentity where id=:noteId")
    suspend fun get(noteId: Int): NoteEntity

    @Insert
    suspend fun save(note: NoteEntity)

}