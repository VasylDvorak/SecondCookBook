package com.example.cookbook.data.room.dao

import androidx.room.*
import com.example.cookbook.data.room.RoomMenu


@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemMenu: RoomMenu)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg menu: RoomMenu)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(menu: List<RoomMenu>)

    @Update
    fun update(itemMenu: RoomMenu)

    @Update
    fun update(vararg menu: RoomMenu)

    @Update
    fun update(menu: List<RoomMenu>)

    @Delete
    fun delete(itemMenu: RoomMenu)

    @Delete
    fun delete(vararg menu: RoomMenu)

    @Delete
    fun delete(menu: List<RoomMenu>)

    @Query("SELECT * FROM RoomMenu")
    fun getAll(): List<RoomMenu>

    @Query("SELECT * FROM RoomMenu WHERE idCategory = :idCategory")
    fun findForCategory(idCategory: String): List<RoomMenu>


}