package com.diegoribeiro.mealsproject.ui.categories

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.diegoribeiro.mealsproject.R
import com.diegoribeiro.mealsproject.data.remote.ResourceNetwork
import com.diegoribeiro.mealsproject.databinding.FragmentCategoriesBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    private val mAdapter: AdapterCategory by lazy { AdapterCategory() }
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var viewModelCategory: ViewModelCategory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelCategory = ViewModelProvider(requireActivity()).get(ViewModelCategory::class.java)
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)

        setupRecyclerView()
        observeViewModel()
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_favorite -> {findNavController().navigate(R.id.action_categoriesFragment_to_favoriteFragment)}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView(){
        recyclerView = binding.rvList
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


}