package com.diegoribeiro.mealsproject.ui.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoribeiro.mealsproject.databinding.FragmentRecipeBinding


class RecipeFragment : Fragment() {
    private val args: RecipeFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(layoutInflater, container, false)


        binding.tvTitle.text = args.currentMeal.strMeal
        //binding.tvLabelModoPreparo.text = args.currentMeal.strInstructions
        Log.d("***Recipe", args.currentMeal.strCategory)

        Glide.with(binding.ivDetails)
            .load(args.currentMeal.strMealThumb)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.ivDetails)




        return binding.root
    }


}