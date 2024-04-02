package com.zybooks.sdsuccc.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cclass(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @NonNull
    var text: String,

    @ColumnInfo(name = "updated")
    var updateTime: Long = System.currentTimeMillis()) {
}