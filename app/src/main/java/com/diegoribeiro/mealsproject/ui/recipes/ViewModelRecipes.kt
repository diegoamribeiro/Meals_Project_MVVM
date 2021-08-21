package com.diegoribeiro.mealsproject.ui.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoribeiro.mealsproject.data.model.Meal
import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelRecipes: ViewModel() {
    private var repository = Repository
    val mealById: MutableLiveData<ResourceNetwork<Meal>> = MutableLiveData()
    private var mealResponse: Meal? = null

    init{
        mealById
    }

    fun getMealById(id: String){
        viewModelScope.launch {
            val response = repository.getMealById(id)
            mealById.postValue(handleResponse(response))
            //mealById.postValue(ResourceNetwork.Loading())
        }
    }


    private fun handleResponse(response: Response<Meal>): ResourceNetwork<Meal>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                if (mealResponse == null){
                    mealResponse = resultResponse
                }
                return ResourceNetwork.Success(mealResponse ?: resultResponse)
            }
        }
        return ResourceNetwork.Error(response.message())
    }



}