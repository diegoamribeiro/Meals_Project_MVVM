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
//        //binding.tvLabelModoPreparo.text = args.currentMeal.strInstructions
//        Log.d("***Recipe", args.currentMeal.strCategory)
        getMealById()

        return binding.root
    }

    private fun getMealById(){
        Log.d("***Recipe", args.currentMeal.idMeal)
        viewModelRecipes.getMealById(args.currentMeal.idMeal)
        viewModelRecipes.mealById.observe(viewLifecycleOwner, {response->
            when(response){
                is ResourceNetwork.Success->{
                    response.data?.let { meal->

                        binding.tvTitle.text = meal.idMeal
                        Glide.with(binding.ivDetails)
                            .load(meal.strMealThumb)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(binding.ivDetails)

                    }
                }
                is ResourceNetwork.Error->{
                    response.message?.let{message->
                        Toast.makeText(requireContext(), "Error $message", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}