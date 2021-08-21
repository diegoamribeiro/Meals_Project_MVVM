package com.diegoribeiro.mealsproject.ui.meals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.databinding.FragmentMealsBinding


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
        viewModel = ViewModelMeals()
        binding = FragmentMealsBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        loadViewModel()

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

    private fun loadViewModel() {
        viewModel.getMealsByCategory(args.currentCategory.strCategory)
        viewModel.listMeals.observe(viewLifecycleOwner, { response ->
            when (response) {
                is ResourceNetwork.Success -> {
                    binding.rvMeals.hideShimmer()
                    response.data?.let { result ->
                        mAdapterMeals.setData(result.meals)
                        Log.d("***Meals", result.meals.toString())
                    }
                }
                is ResourceNetwork.Error -> {
                    response.message?.let { message ->
                        binding.rvMeals.hideShimmer()
                        Toast.makeText(requireContext(), "Error $message", Toast.LENGTH_LONG).show()
                    }
                }
                is ResourceNetwork.Loading -> {
                    binding.rvMeals.showShimmer()
                }
            }
        })
    }
}