package com.example.documentholder.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = [NoteDb::class,PassDb::class,CardDb::class,DocumentDb::class], version = 1)
abstract class Maindb : RoomDatabase() {
    abstract fun getDao(): Dao



    companion object{
        fun db(context:Context): Maindb{
            return Room.databaseBuilder(
                context.applicationContext,
                Maindb::class.java,
                "DataBaseFile.db").build()
        }
    }
}
