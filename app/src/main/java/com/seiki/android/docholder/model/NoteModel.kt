package com.seiki.android.docholder.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "Note")
class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var text: String,
    var uri: Int
): Serializable