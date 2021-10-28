package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateRecipe : AppCompatActivity() {
    //step 2: Do your UI, declare and initialize them in your activity
    //my views
    lateinit var btnUpdate: Button
    lateinit var edTitleUp: EditText
    lateinit var edAuthorNameUp: EditText
    lateinit var edIngredentsUp: EditText
    lateinit var edInstructionUp: EditText

    //my variables
    var title=""; var AuthorName=""; var Ingredents=""; var Instructions=""

    //step 7: Managing Data
    //step 7- 1) create an instance of the database.
    lateinit var ReipesDB:RecipesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_recipe)

        //step 7- 1) create an instance of the database.
        ReipesDB= RecipesDatabase.getInstance(this)

        //step 2
        btnUpdate=findViewById(R.id.btnUpdate)
        edTitleUp=findViewById(R.id.edTitleUp)
        edAuthorNameUp=findViewById(R.id.edAuthorNameUp)
        edIngredentsUp=findViewById(R.id.edIngredentsUp)
        edInstructionUp=findViewById(R.id.edInstructionUp)

        btnUpdate.setOnClickListener(){
            if (edTitleUp.text.isNotEmpty() && edAuthorNameUp.text.isNotEmpty() &&
                edIngredentsUp.text.isNotEmpty() && edInstructionUp.text.isNotEmpty() ){
                title=edTitleUp.text.toString(); AuthorName=edAuthorNameUp.text.toString()
                Ingredents=edIngredentsUp.text.toString(); Instructions=edInstructionUp.text.toString()
                if (intent.extras != null){
                    var id=intent.getIntExtra("id",0)
                    CoroutineScope(Dispatchers.IO).launch {
                        ReipesDB.getRecipesDao().updateRec(RecipesTable(id, title, AuthorName, Ingredents, Instructions))
                    }
                    Toast.makeText(applicationContext, "data are up-to-data successfully!", Toast.LENGTH_SHORT).show()
                    clearEditText()
                    intent= Intent(this,ViewRecipes::class.java)
                    startActivity(intent)
                }else
                    Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }else
                Toast.makeText(applicationContext, "please fill the missing entry!", Toast.LENGTH_SHORT).show()
        }//end lis


    }//end onCreate()

    fun clearEditText(){
        edTitleUp.text.clear()
        edAuthorNameUp.text.clear()
        edIngredentsUp.text.clear()
        edInstructionUp.text.clear()
    }
}