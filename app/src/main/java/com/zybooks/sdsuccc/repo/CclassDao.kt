package com.zybooks.sdsuccc.repo

import androidx.room.*
import com.zybooks.sdsuccc.model.Cclass

@Dao
interface CclassDao {
    @Query("SELECT * FROM Cclass WHERE id = :id")
    fun getCclass(id: Long): Cclass?

    @Query("SELECT * FROM Cclass ORDER BY text COLLATE NOCASE")
    fun getCclasses(): List<Cclass>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCclass(cclass: Cclass): Long

    @Update
    fun updateCclass(cclass: Cclass)

    @Delete
    fun deleteCclass(cclass: Cclass)
}