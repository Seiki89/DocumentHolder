package com.example.documentholder.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
//заметки
@Entity(tableName = "Note")
data class NoteDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var text: String,
    var uri: Int
)

//пароли
@Entity(tableName = "Pass")
data class PassDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val service: String,
    val login: String,
    val password: String
)
//карточки
@Entity(tableName = "Card")
data class CardDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val bank: String,
    val num1: String,
    val num2: String,
    val num3: String,
    val num4: String,
    val date1: String,
    val date2: String,
    val name: String,
    val cvc: String,
    val pin: String,
)
@Entity(tableName = "Document")
data class DocumentDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var type: String,
    var dateEnd: String,
    var NameDoc: String,
    var fio :String,
    var birth_date :String,
    var birth_pos :String,
    var sex :String,
    var a1 :String,
    var a2 :String,
    var a3 :String,
    var a4 :String,
    var a5 :String,
    var a6 :String,
    var a7 :String,
    var a8 :String,
    var a9 :String,
    var a10 :String,
    var a11 :String,
    var a12 :String,
    var a13 :String,
    var a14 :String,
    var a15 :String,
    var a16 :String,
    var a17 :String,
    var a18 :String,
    var a19 :String,
    var a20 :String,
    var a21 :String,
    var a22 :String,
    var a23 :String,
    var a24 :String,
    var a25 :String,
    var a26 :String,
    var a27 :String,
    var a28 :String,
    var a29 :String,
    var a30 :String,
    var a31 :String,
    var a32 :String,
    var a33 :String,
    var a34 :String,
    var a35 :String,
    var a36 :String,
    var a37 :String,
    var a38 :String,
    var a39 :String,
    var a40 :String,
    var ico :Int,
    var idLD:Int,
    var bgDoc:Int,
)
