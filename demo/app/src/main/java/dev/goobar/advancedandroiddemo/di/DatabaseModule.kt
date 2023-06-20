package dev.goobar.advancedandroiddemo.di

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.goobar.advancedandroiddemo.BuildConfig
import dev.goobar.advancedandroiddemo.db.AppDatabase
import dev.goobar.advancedandroiddemo.db.NoteDao
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        val supportFactory =
            SupportFactory(SQLiteDatabase.getBytes("password".toCharArray()))
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "android-study-guide-database"
        ).openHelperFactory(supportFactory).build()
    }
}