package com.zybooks.sdsuccc.repo

import androidx.room.*
import com.zybooks.sdsuccc.model.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person WHERE id = :id")
    fun getPerson(id: Long): Person?

    @Query("SELECT * FROM Person WHERE cclass_id = :cclassId ORDER BY id")
    fun getPersons(cclassId: Long): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPerson(person: Person): Long

    @Update
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)
}

