package com.zybooks.sdsuccc.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = Cclass::class,
        parentColumns = ["id"],
        childColumns = ["cclass_id"],
        onDelete = CASCADE)
])

data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String = "",
    var dob: String = "",
    var allergy: String = "",
    @ColumnInfo(name = "cclass_id")
    var cclassId: Long = 0) {
}