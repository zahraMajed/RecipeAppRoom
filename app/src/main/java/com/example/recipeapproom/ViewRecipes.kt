package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_view_recipes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewRecipes : AppCompatActivity() {

    //step 7: Managing Data
    //step 7- 1) create an instance of the database.
    lateinit var ReipesDB:RecipesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_recipes)

        //step 7- 1) create an instance of the database.
        ReipesDB= RecipesDatabase.getInstance(this)

        getRecipes()


    }

    fun getRecipes(){
        CoroutineScope(Dispatchers.IO).launch{
            withContext(Dispatchers.Main){
                //2) call Dao method that you need (here getNotes)
                if (!ReipesDB.getRecipesDao().getAll().isNullOrEmpty()) {
                    rv_main.adapter=ReyclerAdapter(this@ViewRecipes, ReipesDB.getRecipesDao().getAll())
                    rv_main.layoutManager= LinearLayoutManager(this@ViewRecipes)
                }else{
                    intent= Intent(this@ViewRecipes,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(applicationContext, "No Recipes!", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }//end getRecipes()
    
    fun deleteRecipe(recipe: RecipesTable){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                ReipesDB.getRecipesDao().delRec(recipe)
                Toast.makeText(applicationContext, "${recipe.title} Recipe has deleted successfully!", Toast.LENGTH_SHORT).show()
                getRecipes()
            }
        }
    }
}