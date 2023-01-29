package com.seiki.android.docholder.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Settings")
data class SettModel(
    @PrimaryKey
    var id: Int = 0,
    var pass:Int,
    var switchPass:Boolean,
    var switchBio:Boolean,
)

