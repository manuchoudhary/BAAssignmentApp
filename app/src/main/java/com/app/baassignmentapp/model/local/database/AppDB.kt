package com.app.baassignmentapp.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.baassignmentapp.model.local.dao.ArticleDao
import com.app.baassignmentapp.model.local.entity.ApiResponseEntity

@Database(
    entities = [ApiResponseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDB : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    companion object {
        private var INSTANCE: AppDB? = null
        const val DB_NAME = "AppDB"

        fun getInstance(context: Context) : AppDB {
            if (INSTANCE == null) {
                synchronized(AppDB::class) {
                    val builder = Room.databaseBuilder(context, AppDB::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                    INSTANCE = builder.build()
                }
            }
            return INSTANCE!!
        }
    }
}