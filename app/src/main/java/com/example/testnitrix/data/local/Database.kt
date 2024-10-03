package com.example.testnitrix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testnitrix.data.model.VideoResponseItem

@Database(entities = [VideoResponseItem::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun videoDao(): Dao
}