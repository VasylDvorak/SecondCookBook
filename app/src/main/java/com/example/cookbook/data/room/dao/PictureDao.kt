package com.example.cookbook.data.room.dao

import androidx.room.*
import com.example.cookbook.data.room.RoomPicture


@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: RoomPicture)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg categories: RoomPicture)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categories: List<RoomPicture>)

    @Update
    fun update(category: RoomPicture)

    @Update
    fun update(vararg categories: RoomPicture)

    @Update
    fun update(categories: List<RoomPicture>)

    @Delete
    fun delete(category: RoomPicture)

    @Delete
    fun delete(vararg categories: RoomPicture)

    @Delete
    fun delete(categories: List<RoomPicture>)

    @Query("DELETE FROM RoomPicture")
    fun deleteAll()

    @Query("SELECT * FROM RoomPicture")
    fun getAll(): List<RoomPicture>

    @Query("SELECT * FROM RoomPicture WHERE idCategory = :idCategory")
    fun findForCategory(idCategory: String): RoomPicture


}