package com.src.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room database declaration. Contains the LectureInfo entity and is versioned at 1.
 */
@Database(entities = [LectureInfo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lectureInfoDao(): LectureInfo
}
