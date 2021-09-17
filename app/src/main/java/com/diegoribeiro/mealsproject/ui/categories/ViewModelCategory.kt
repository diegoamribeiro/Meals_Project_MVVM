package com.diegoribeiro.mealsproject.ui.categories


import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegoribeiro.mealsproject.data.local.CategoriesEntity
import com.diegoribeiro.mealsproject.data.model.Categories
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelCategory @Inject constructor(
    private val repository: Repository,
    application: Application
): AndroidViewModel(application){

    /** DATABASE */

    private fun insertCategories(categoriesEntity: CategoriesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertCategories(categoriesEntity)
        }
    }

    private fun offLineCacheCategories(categories: Categories){
        val categoriesEntity = CategoriesEntity(categories)
        insertCategories(categoriesEntity)
    }

    /** RETROFIT */
    val listCategory: MutableLiveData<ResourceNetwork<Categories>> = MutableLiveData()
    private var categoryResponse: Categories? = null

    init {
        getAllCategories()
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            listCategory.postValue(ResourceNetwork.Loading())
            val response = repository.remote.getCategories()
            listCategory.postValue(handleCategoryResponse(response))
            val categoriesResponse = listCategory.value
            if (categoriesResponse != null){
                offLineCacheCategories(categoryResponse!!)
            }
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