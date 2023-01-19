package com.seiki.android.docholder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seiki.android.docholder.model.CardModel
import com.seiki.android.docholder.model.DocModel
import com.seiki.android.docholder.model.NoteModel
import com.seiki.android.docholder.model.PassModel

@Dao
interface DAO {
//NoteModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)
    @Query("SELECT * FROM Note")
    fun getNote(): LiveData<List<NoteModel>>
    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

//PassModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPass(passModel: PassModel)
    @Query("SELECT * FROM Pass")
    fun getPass(): LiveData<List<PassModel>>
    @Delete
    suspend fun deletePass(passModel: PassModel)

//CardModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(cardModel: CardModel)
    @Query("SELECT * FROM Card")
    fun getCard(): LiveData<List<CardModel>>
    @Delete
    suspend fun deleteCard (cardModel: CardModel)

//DocModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoc(docModel: DocModel)
    @Query("SELECT * FROM Document")
    fun getDoc(): LiveData<List<DocModel>>
    @Query("SELECT (SELECT COUNT(*) FROM document) == 0")
    fun isEmptyDoc(): Boolean
    @Delete
    fun deleteDoc (docModel: DocModel)
}
