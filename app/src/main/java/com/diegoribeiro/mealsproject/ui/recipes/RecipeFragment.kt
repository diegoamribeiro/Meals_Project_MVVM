package com.diegoribeiro.mealsproject.ui.recipes

import android.os.Bundle
import android.os.MessageQueue
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoribeiro.mealsproject.databinding.FragmentRecipeBinding


class RecipeFragment : Fragment() {


    private lateinit var viewModelRecipes: ViewModelRecipes
    private val args: RecipeFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeBinding
    private val ingredientAdapter: IngredientAdapter by lazy { IngredientAdapter() }
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater, container, false)
        viewModelRecipes = ViewModelRecipes()
        handleObserver()
        viewModelRecipes.getMealById(args.currentMeal.idMeal)
        Log.d("***Recipe", args.currentMeal.idMeal)
        setupRecyclerView()


        return binding.root
    }

    private fun setupRecyclerView(){
        recyclerView = binding.rvList
        recyclerView.adapter = ingredientAdapter
        recyclerView.layoutManager = object : LinearLayoutManager(requireContext()){
            override fun canScrollVertically(): Boolean { return false }
        }

        viewModelRecipes.ingredient.observe(viewLifecycleOwner,{listIngredients->
            listIngredients.let { result->
                ingredientAdapter.setData(result)
            }
        })
    }


    private fun handleObserver(){
        viewModelRecipes.recipeById.observe(viewLifecycleOwner, { response->
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