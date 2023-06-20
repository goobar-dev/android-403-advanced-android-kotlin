package dev.goobar.advancedandroiddemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.goobar.advancedandroiddemo.data.NoteEntity

@Database(
    version = 1,
    entities = [NoteEntity::class],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}