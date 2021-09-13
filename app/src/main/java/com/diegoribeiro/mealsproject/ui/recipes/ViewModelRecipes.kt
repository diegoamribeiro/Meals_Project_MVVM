package com.diegoribeiro.mealsproject.ui.recipes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.diegoribeiro.mealsproject.data.model.Ingredient
import com.diegoribeiro.mealsproject.data.model.Meal
import com.diegoribeiro.mealsproject.data.model.Recipes
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelRecipes @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application)  {

    val recipeById: MutableLiveData<ResourceNetwork<Recipes>> = MutableLiveData()
    var recipeResponse: Recipes? = null

    val ingredient: MutableLiveData<List<Ingredient>> = MutableLiveData()

    init{
        recipeById
    }

    fun getMealById(id: String){
        viewModelScope.launch {
            repository.remote.getAllMealsById(id).let { recipes ->
                recipeById.postValue(handleResponse(recipes))
                val ing = recipes.body()
                ingredient.postValue(ing!!.meals[0].filterBlankIngredient())
            }
        }
    }

    fun saveMealToFavorite(meal: Meal){
        //mealsDao.insertMeal(meal)
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