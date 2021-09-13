package com.diegoribeiro.mealsproject.ui.recipes


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoribeiro.mealsproject.databinding.FragmentRecipeBinding
import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import com.diegoribeiro.mealsproject.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        viewModelRecipes = ViewModelProvider(requireActivity()).get(ViewModelRecipes::class.java)
        handleObserver()
        viewModelRecipes.getMealById(args.currentMeal.idMeal)
        Log.d("***Recipe", args.currentMeal.idMeal)
        setupRecyclerView()

        changeColor()
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

    private fun changeColor(){
        binding.cbFavorite.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked){
                Snackbar.make(compoundButton, "Recipe added to favorites", Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(compoundButton, "Recipe removed from favorites", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleObserver(){
        viewModelRecipes.recipeById.observe(viewLifecycleOwner, { response->
            response.data.let { recipe->
                Log.d("***Recipe", recipe.toString())
                binding.tvTitle.text = recipe!!.meals[0].strMeal
                binding.tvInstructions.text = recipe.meals[0].strInstructions
                Glide.with(binding.ivDetails)
                    .load(recipe.meals[0].strMealThumb)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivDetails)
            }
        })
    }
}