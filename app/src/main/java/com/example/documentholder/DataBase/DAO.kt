package com.example.documentholder.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {
//NoteDB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteDb: NoteDb)

    @Query("SELECT * FROM Note")
    fun getNote(): List<NoteDb>

    @Query("DELETE FROM Note WHERE id = :userId")
    fun deleteByNoteId(userId: Int?)
//PassDB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPass(passDb: PassDb)

    @Query("SELECT * FROM Pass")
    fun getPass(): List<PassDb>

    @Query("DELETE FROM Pass WHERE id = :userId")
    fun deleteByPassId(userId: Int?)
//CardDB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(cardDb: CardDb)

    @Query("SELECT * FROM Card")
    fun getCard(): List<CardDb>

    @Query("DELETE FROM Card WHERE id = :userId")
    fun deleteByCardId(userId: Int?)
//DocumentDB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoc(documentDb: DocumentDb)

    @Query("SELECT * FROM Document")
    fun getDoc(): List<DocumentDb>

    @Query("DELETE FROM Document WHERE id = :id")
    fun deleteByDocId(id: Int?)

    @Query("SELECT (SELECT COUNT(*) FROM document) == 0")
    fun isEmptyDoc(): Boolean

    @Delete
    fun delDoc (documentDb: DocumentDb)


}

