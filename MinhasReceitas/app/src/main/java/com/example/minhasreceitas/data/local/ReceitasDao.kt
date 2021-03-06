package com.example.minhasreceitas.data.local

import androidx.room.*
import com.example.minhasreceitas.data.remote.Meal

@Dao
interface ReceitasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToDB(al: ArrayList<Meal>)

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM recipes WHERE id=:id")
    suspend fun findId(id: String): List<Meal>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM recipes WHERE favourite=:favourite")
    suspend fun findFavouriteRecipes(favourite: String): List<Meal>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM recipes WHERE cuisine=:cuisine")
    suspend fun loadCuisineDB(cuisine: String): List<Meal>

    @Query("DELETE FROM recipes WHERE name= :currentMealName")
    fun deleteRow(currentMealName: String)

}