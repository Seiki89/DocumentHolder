package com.seiki.android.docholder.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Card")
class CardModel(
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
) : Serializable