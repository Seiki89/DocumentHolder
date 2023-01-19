package com.seiki.android.docholder.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Pass")
class PassModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val service: String,
    val login: String,
    val password: String
): Serializable