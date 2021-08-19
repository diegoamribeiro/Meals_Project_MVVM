package com.diegoribeiro.mealsproject.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private val mAdapter: AdapterCategory by lazy { AdapterCategory() }
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var viewModelCategory: ViewModelCategory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelCategory = ViewModelCategory()
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModelCategory.listCategory.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is ResourceNetwork.Success->{
                    binding.rvList.hideShimmer()
                    response.data?.let {allResults->
                        mAdapter.setData(allResults.categories)
                    }
                }
                is ResourceNetwork.Error->{
                    binding.rvList.hideShimmer()
                    response.message?.let { message->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is ResourceNetwork.Loading->{
                    binding.rvList.showShimmer()
                }
            }
        })
    }

    private fun setupRecyclerView(){
        recyclerView = binding.rvList
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}