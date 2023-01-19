package com.seiki.android.docholder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.seiki.android.docholder.model.CardModel
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.model.NoteModel
import com.seiki.android.docholder.model.PassModel

@Database(entities = [NoteModel::class,PassModel::class,CardModel::class,DocModel::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): DAO

    companion object {
        private var database: MainDb? = null

        @Synchronized
        fun db(context: Context): MainDb {
            return if (database == null) {
                database = Room.databaseBuilder(context, MainDb::class.java ,"DataBaseFile.db").build()
                database as MainDb
            } else {
                database as MainDb
            }
        }
    }
}
