package com.diegoribeiro.mealsproject.ui.categories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoribeiro.mealsproject.data.local.CategoriesEntity
import com.diegoribeiro.mealsproject.data.local.LocalRepository
import com.diegoribeiro.mealsproject.data.local.MealsDao
import com.diegoribeiro.mealsproject.data.local.MealsDatabase
import com.diegoribeiro.mealsproject.data.model.Categories
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.data.repository.Repository
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelCategory(): ViewModel(){

    private val repository = Repository
    val listCategory: MutableLiveData<ResourceNetwork<Categories>> = MutableLiveData()
    private var categoryResponse: Categories? = null

    //val readCategories: LiveData<List<CategoriesEntity>>

    init {
        getAllCategories()
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            listCategory.postValue(ResourceNetwork.Loading())
            val response = repository.getAllCategories()
            listCategory.postValue(handleCategoryResponse(response))
        }
    }


    private fun handleCategoryResponse(response: Response<Categories>): ResourceNetwork<Categories>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
                if (categoryResponse == null){
                    categoryResponse = resultResponse
                }
                return ResourceNetwork.Success(categoryResponse ?: resultResponse)
            }
        }
        return ResourceNetwork.Error(response.message())
    }
}