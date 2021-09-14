package com.diegoribeiro.mealsproject.ui.meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoribeiro.mealsproject.R
import com.diegoribeiro.mealsproject.data.model.Meal
import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.databinding.MealsItemBinding
import com.diegoribeiro.mealsproject.utils.DiffUtilGeneric

class AdapterMeals: RecyclerView.Adapter<AdapterMeals.MealsViewHolder>() {

    private var mealsList = listOf<Meal>()

    class MealsViewHolder(val binding: MealsItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val binding = MealsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.binding.apply {
            tvMeal.text = mealsList[position].strMeal
            Glide.with(ivMeal)
                .load(mealsList[position].strMealThumb)
                .error(R.drawable.ic_broken)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivMeal)
        }

        holder.itemView.setOnClickListener {
            val action = MealsFragmentDirections.actionMealsFragmentToRecipeFragment(mealsList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }
    override fun getItemCount() = mealsList.size

    fun setData(list: List<Meal>){
        val mealDiffUtil = DiffUtilGeneric(mealsList, list)
        val mealResult = DiffUtil.calculateDiff(mealDiffUtil)
        this.mealsList = list
        mealResult.dispatchUpdatesTo(this)
    }
}