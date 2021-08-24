package com.diegoribeiro.mealsproject.ui.recipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoribeiro.mealsproject.data.model.Ingredient
import com.diegoribeiro.mealsproject.data.model.Recipes
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelRecipes: ViewModel() {
    private var repository = Repository
    val recipeById: MutableLiveData<ResourceNetwork<Recipes>> = MutableLiveData()
    var recipeResponse: Recipes? = null

    val ingredient: MutableLiveData<List<Ingredient>> = MutableLiveData()
    //private var mealResponse: Meal? = null

    init{
        recipeById
    }

    fun getMealById(id: String){
        viewModelScope.launch {
            repository.getMealById(id).let { recipes ->
                recipeById.postValue(handleResponse(recipes))

                //ingredient.postValue(handleResponse(recipes.meals[0].filterBlankIngredient()))

            }
        }
    }


    private fun handleResponse(response: Response<Recipes>): ResourceNetwork<Recipes>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                if (recipeResponse == null){
                    recipeResponse = resultResponse
                }
                return ResourceNetwork.Success(recipeResponse ?: resultResponse)
            }
        }
        return ResourceNetwork.Error(response.message())
    }



}