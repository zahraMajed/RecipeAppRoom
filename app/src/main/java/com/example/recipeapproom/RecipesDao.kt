package com.example.recipeapproom

import androidx.room.*

//step 5: create Dao interface (Data Access Objects interface)
//this interface is used to access database in better and modular way as compared to query builders or direct queries.

// 1) in this Dao interface you should use @Dao annotation to define the interface as Dao
@Dao
interface RecipesDao {
    //2) define your methods to interact with the data in your app's database.

    //method to get all data:
    @Query("SELECT * FROM Recipes")
    fun getAll(): List<RecipesTable>

    //insert a row into the table
    @Insert
    fun insertRec(recipe:RecipesTable)

    //update note
    @Update
    fun updateRec(recipe: RecipesTable)

    //delete note row
    @Delete
    fun delRec(recipe: RecipesTable)
}