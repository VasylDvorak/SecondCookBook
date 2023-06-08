package com.example.cookbook.data.room.dao

import androidx.room.*
import com.example.cookbook.data.room.RoomCategory

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: RoomCategory)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg categories: RoomCategory)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(categories: List<RoomCategory>)

    @Update
    fun update(category: RoomCategory)

    @Update
    fun update(vararg categories: RoomCategory)

    @Update
    fun update(categories: List<RoomCategory>)

    @Delete
    fun delete(category: RoomCategory)

    @Delete
    fun delete(vararg categories: RoomCategory)

    @Delete
    fun delete(categories: List<RoomCategory>)

    @Query("SELECT * FROM RoomCategory")
    fun getAll(): List<RoomCategory>

    @Query("SELECT * FROM RoomCategory WHERE strCategory = :strCategory LIMIT 1")
    fun findByCategory(strCategory: String): RoomCategory


}