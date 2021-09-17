package com.diegoribeiro.mealsproject.ui.meals

import android.app.Application
import androidx.lifecycle.*
import com.diegoribeiro.mealsproject.data.local.MealsEntity
import com.diegoribeiro.mealsproject.data.model.Meals
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelMeals @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application) {

    /** ROOM DATABASE */
    val readMeals: LiveData<List<MealsEntity>> = repository.local.readMealsDatabase().asLiveData()

    private fun insertMeals(mealsEntity: MealsEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertMeals(mealsEntity)
        }

    /** RETROFIT */
    val listMeals: MutableLiveData<ResourceNetwork<Meals>> = MutableLiveData()
    private var mealsResponse: Meals? = null

    init {
        listMeals
    }

    fun getMealsByCategory(category: String) {
        viewModelScope.launch {
            listMeals.postValue(ResourceNetwork.Loading())
            val response = repository.remote.getAllMealsByCategory(category)
            listMeals.postValue(handleMealsResponse(response))
            val mealsResponse = listMeals.value!!.data
            if (mealsResponse != null){
                offlineCacheMeals(mealsResponse)
            }
        }
    }

    private fun offlineCacheMeals(mealEntity: Meals) {
        val mealsEntity = MealsEntity(mealEntity)
        insertMeals(mealsEntity)
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