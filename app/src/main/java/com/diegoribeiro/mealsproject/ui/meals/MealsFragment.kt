package com.diegoribeiro.mealsproject.ui.meals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.databinding.FragmentMealsBinding
import com.diegoribeiro.mealsproject.utils.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealsFragment : Fragment() {

    private val mAdapterMeals: AdapterMeals by lazy { AdapterMeals() }
    private lateinit var viewModel: ViewModelMeals
    private val args: MealsFragmentArgs by navArgs()
    private lateinit var binding: FragmentMealsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(ViewModelMeals::class.java)
        binding = FragmentMealsBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        readDatabase()


        Toast.makeText(requireContext(), args.currentCategory.strCategory, Toast.LENGTH_LONG).show()
        return binding.root
    }

    private fun setupRecyclerView() {
        recyclerView = binding.rvMeals
        recyclerView.apply {
            adapter = mAdapterMeals
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun readDatabase() {
            lifecycleScope.launch {
                viewModel.readMeals.observeOnce(viewLifecycleOwner,  {mealsDatabase ->
                    Log.d("***readDatabase", "readDatabase called")
                    if (mealsDatabase.isNotEmpty()){
                        mAdapterMeals.setData(mealsDatabase[0].meals.meals)
                        binding.rvMeals.hideShimmer()
                    }else{
                        requestApiData()
                    }
                })
            }
    }

    private fun loadDataFromCache(){
        lifecycleScope.launch {
            viewModel.readMeals.observe(viewLifecycleOwner, { database->
                if (database.isNotEmpty()){
                    mAdapterMeals.setData(database[0].meals.meals)
                }
            })
        }
    }

    private fun requestApiData() {
        Log.d("***requestApiData", "requestApiData called")
        viewModel.getMealsByCategory(args.currentCategory.strCategory)
        viewModel.listMeals.observe(viewLifecycleOwner, { response ->
            when (response) {
                is ResourceNetwork.Success -> {
                    binding.rvMeals.hideShimmer()
                    response.data?.let { result ->
                        mAdapterMeals.setData(result.meals)
                    }
                }
                is ResourceNetwork.Error -> {
                    binding.rvMeals.hideShimmer()
                    loadDataFromCache()
                    Toast.makeText(requireContext(), "Error ${response.message}", Toast.LENGTH_LONG).show()
                }
                is ResourceNetwork.Loading -> {
                    binding.rvMeals.showShimmer()
                }
            }
        })
    }
}