package com.diegoribeiro.mealsproject.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diegoribeiro.mealsproject.R
import com.diegoribeiro.mealsproject.data.model.Category
import com.diegoribeiro.mealsproject.databinding.CategoryItemBinding
import com.diegoribeiro.mealsproject.utils.DiffUtilGeneric

class AdapterCategory: RecyclerView.Adapter<AdapterCategory.CategoryViewHolder>() {

    private var categoryList = emptyList<Category>()

    class CategoryViewHolder(val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = categoryList[position].strCategory
            Glide.with(ivCategory)
                .load(categoryList[position].strCategoryThumb)
                .error(R.drawable.ic_broken)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivCategory)
        }

        holder.itemView.setOnClickListener {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToMealsFragment(categoryList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(list: List<Category>){
        val categoryDiffUtil = DiffUtilGeneric(categoryList, list)
        val categoryResult = DiffUtil.calculateDiff(categoryDiffUtil)
        this.categoryList = list
        categoryResult.dispatchUpdatesTo(this)
    }
    override fun getItemCount() = categoryList.size

}