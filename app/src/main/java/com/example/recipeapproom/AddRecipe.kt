package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRecipe : AppCompatActivity() {
    //step 2: Do your UI, declare and initialize them in your activity
    //my views
    lateinit var btnSaveInsert: Button
    lateinit var btnViewInsert: Button
    lateinit var edTitleInsert: EditText
    lateinit var edAuthorNameInsert: EditText
    lateinit var edIngredentsInsert: EditText
    lateinit var edInstructionInsert: EditText

    //my variables
    var title=""; var AuthorName=""; var Ingredents=""; var Instructions=""

    //step 7: Managing Data
    //step 7- 1) create an instance of the database.
    lateinit var ReipesDB:RecipesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        //step 7- 1) create an instance of the database.
        ReipesDB= RecipesDatabase.getInstance(this)

        //step 2
        btnSaveInsert=findViewById(R.id.btnSaveInsert)
        btnViewInsert=findViewById(R.id.btnViewInsert)

        edTitleInsert=findViewById(R.id.edTitleInsert)
        edAuthorNameInsert=findViewById(R.id.edAuthorNameInsert)
        edIngredentsInsert=findViewById(R.id.edIngredentsInsert)
        edInstructionInsert=findViewById(R.id.edInstructionInsert)

        btnSaveInsert.setOnClickListener(){
            if (edTitleInsert.text.isNotEmpty() && edAuthorNameInsert.text.isNotEmpty() &&
                edIngredentsInsert.text.isNotEmpty() && edInstructionInsert.text.isNotEmpty() ){
                title=edTitleInsert.text.toString()
                AuthorName=edAuthorNameInsert.text.toString()
                Ingredents=edIngredentsInsert.text.toString()
                Instructions=edInstructionInsert.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    ReipesDB.getRecipesDao()
                        .insertRec(RecipesTable(0, title, AuthorName, Ingredents, Instructions))
                }
                Toast.makeText(applicationContext, "data saved successfully!", Toast.LENGTH_SHORT).show()
                clearEditText()
            }else
                Toast.makeText(applicationContext, "please fill the missing entry!", Toast.LENGTH_SHORT).show()
        }//end btnSave lis

        btnViewInsert.setOnClickListener(){
            if (isDBnotEmpty()){
                intent= Intent(this,ViewRecipes::class.java)
                startActivity(intent)
            }else
                Toast.makeText(applicationContext, "You have to add 1 recipe at least!", Toast.LENGTH_SHORT).show()
        }

    }//end onCreate()

    fun clearEditText(){
        edTitleInsert.text.clear()
        edAuthorNameInsert.text.clear()
        edIngredentsInsert.text.clear()
        edInstructionInsert.text.clear()
    }

    fun isDBnotEmpty(): Boolean {
        var isDBnotEmpty:Boolean
            //2) call Dao method that you need (here getAll)
            if (!ReipesDB.getRecipesDao().getAll().isNullOrEmpty()){
                isDBnotEmpty=true
            }else
                isDBnotEmpty=false

        return isDBnotEmpty
    }//end fun
}