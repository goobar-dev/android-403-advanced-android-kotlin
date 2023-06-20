package dev.goobar.advancedandroiddemo.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.goobar.advancedandroiddemo.data.NoteEntity
import dev.goobar.advancedandroiddemo.data.RepoEntity

@Database(
    version = 2,
    entities = [NoteEntity::class, RepoEntity::class],
    autoMigrations = [
        AutoMigration(from =1, to = 2)
     ],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun repoDao(): RepoDao
}