package com.diegoribeiro.mealsproject.ui.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoribeiro.mealsproject.data.model.Ingredient
import com.diegoribeiro.mealsproject.databinding.IngredientsRecipeItemBinding
import com.diegoribeiro.mealsproject.utils.DiffUtilGeneric

class IngredientAdapter : RecyclerView.Adapter<IngredientAdapter.IngredientAdapterViewHolder>() {

    private var ingredientsList = emptyList<Ingredient>()

    class IngredientAdapterViewHolder(
        private val binding: IngredientsRecipeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ingredient) {
            binding.cbIngredient.text = "${item.measure} - ${item.name}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IngredientsRecipeItemBinding.inflate(inflater, parent, false)
        return IngredientAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientAdapterViewHolder, position: Int) {
        holder.bind(ingredientsList[position])
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setData(list: List<Ingredient>){
        val mealDiffUtil = DiffUtilGeneric(ingredientsList, list)
        val mealResult = DiffUtil.calculateDiff(mealDiffUtil)
        this.ingredientsList = list
        mealResult.dispatchUpdatesTo(this)
    }
}