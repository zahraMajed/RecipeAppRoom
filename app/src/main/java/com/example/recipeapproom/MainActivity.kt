package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    //step 7: Managing Data
    //step 7- 1) create an instance of the database.
    lateinit var ReipesDB:RecipesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //step 7- 1) create an instance of the database.
        ReipesDB= RecipesDatabase.getInstance(this)

        //step 2: Do your UI, declare and initialize them in your activity
        //my views
        val btnInsertMain=findViewById<Button>(R.id.btnInsertMain)
        val btnViewRecMain=findViewById<Button>(R.id.btnViewRecMain)


        btnInsertMain.setOnClickListener(){
         intent= Intent(this,AddRecipe::class.java)
            startActivity(intent)
        }

        btnViewRecMain.setOnClickListener(){
            if (isDBnotEmpty()){
                intent= Intent(this,ViewRecipes::class.java)
                startActivity(intent)
            }else
                Toast.makeText(applicationContext, "You have to add 1 recipe at least!", Toast.LENGTH_SHORT).show()
        }

    }//end onCreate()

    fun isDBnotEmpty(): Boolean {
        var isDBnotEmpty:Boolean
        //2) call Dao method that you need (here getAll)
        if (!ReipesDB.getRecipesDao().getAll().isNullOrEmpty()){
            isDBnotEmpty=true
        }else
            isDBnotEmpty=false
        return isDBnotEmpty
    }//end fun

}//end class