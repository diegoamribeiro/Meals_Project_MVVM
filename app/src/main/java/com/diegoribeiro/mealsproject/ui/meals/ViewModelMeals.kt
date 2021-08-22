package com.diegoribeiro.mealsproject.ui.meals

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelMeals : ViewModel() {

    private val repository = Repository
    val listMeals: MutableLiveData<ResourceNetwork<Meals>> = MutableLiveData()
    private var mealsResponse: Meals? = null

    init {
        listMeals
    }

    fun getMealsByCategory(category: String) {
        viewModelScope.launch {
            listMeals.postValue(ResourceNetwork.Loading())
            val response = repository.getAllMealsByCategory(category)
            listMeals.postValue(handleMealsResponse(response))
        }
    }

    private fun handleMealsResponse(response: Response<Meals>): ResourceNetwork<Meals> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (mealsResponse == null) {
                    mealsResponse = resultResponse
                }
                return ResourceNetwork.Success(mealsResponse ?: resultResponse)
            }
        }
        return ResourceNetwork.Error(response.message())
    }
}