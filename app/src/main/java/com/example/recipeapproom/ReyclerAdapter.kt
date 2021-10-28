package com.example.recipeapproom

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class ReyclerAdapter (val activity: ViewRecipes, val RecipesList:List<RecipesTable>): RecyclerView.Adapter<ReyclerAdapter.RecipeItem>() {
    class RecipeItem (itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReyclerAdapter.RecipeItem {
        return RecipeItem(LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false))
    }

    override fun onBindViewHolder(holder: ReyclerAdapter.RecipeItem, position: Int) {
        val RecipesTableObj= RecipesList[position]

        val title= RecipesList[position].title
        val id =RecipesList[position].id
        holder.itemView.apply {
            btnViewRecRV.setText("$id: $title")

            btnViewRecRV.setOnClickListener(){
                LL1RV.visibility=View.GONE
                LL2RV.visibility=View.VISIBLE
                tvTitle.text= RecipesList[position].title
                tvAuthor.text="By ${RecipesList[position].author}"
                tvIngredentsDB.text= RecipesList[position].Ingredients
                tvInstructionsDB.text= RecipesList[position].Instructions
            }

            LL2RV.setOnClickListener(){
                LL1RV.visibility=View.VISIBLE
                LL2RV.visibility=View.GONE
            }

            btnUpdateRecRV.setOnClickListener(){
                val intent = Intent(activity,UpdateRecipe::class.java)
                intent.putExtra("id",id)
                activity.startActivity(intent)
            }

            btnDelRecRV.setOnClickListener(){
                activity.deleteRecipe(RecipesTableObj)
            }

        }
    }

    override fun getItemCount(): Int = RecipesList.size
}