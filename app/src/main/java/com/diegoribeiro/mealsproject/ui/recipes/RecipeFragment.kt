package com.diegoribeiro.mealsproject.ui.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.databinding.FragmentRecipeBinding


class RecipeFragment : Fragment() {

    private lateinit var viewModelRecipes: ViewModelRecipes
    private val args: RecipeFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater, container, false)
        viewModelRecipes = ViewModelRecipes()
        handleObserver()
        viewModelRecipes.getMealById(args.currentMeal.idMeal)
        Log.d("***Recipe", args.currentMeal.idMeal)



        return binding.root
    }

    private fun handleObserver(){
        viewModelRecipes.mealById.observe(viewLifecycleOwner, {response->

            response.meals.let { recipe->
                Log.d("***Recipe", recipe.toString())
                binding.tvTitle.text = recipe[0].strMeal
                binding.tvInstructions.text = recipe[0].strInstructions
                Glide.with(binding.ivDetails)
                    .load(recipe[0].strMealThumb)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivDetails)
            }
        })
    }
}