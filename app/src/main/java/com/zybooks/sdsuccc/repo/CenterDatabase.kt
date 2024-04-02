package com.zybooks.sdsuccc.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zybooks.sdsuccc.model.Person
import com.zybooks.sdsuccc.model.Cclass

@Database(entities = [Person::class, Cclass::class], version = 1)
abstract class CenterDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun cclassDao(): CclassDao
}