package com.example.cookbook.data.room.dao


import androidx.room.*
import com.example.cookbook.data.room.RoomRecipe


@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemRecipe: RoomRecipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg recipe: RoomRecipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: List<RoomRecipe>)

    @Update
    fun update(itemRecipe: RoomRecipe)

    @Update
    fun update(vararg recipe: RoomRecipe)

    @Update
    fun update(recipe: List<RoomRecipe>)

    @Delete
    fun delete(itemRecipe: RoomRecipe)

    @Delete
    fun delete(vararg recipe: RoomRecipe)

    @Delete
    fun delete(recipe: List<RoomRecipe>)

    @Query("SELECT * FROM RoomRecipe")
    fun getAll(): List<RoomRecipe>

    @Query("SELECT * FROM RoomRecipe WHERE idMeal = :idMeal")
    fun findRecipe(idMeal: String): List<RoomRecipe>


}