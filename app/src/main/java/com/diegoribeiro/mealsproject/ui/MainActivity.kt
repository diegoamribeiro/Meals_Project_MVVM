package com.diegoribeiro.mealsproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.diegoribeiro.mealsproject.R
import com.diegoribeiro.mealsproject.data.model.Categories
import com.diegoribeiro.mealsproject.data.remote.RemoteClient
import com.diegoribeiro.mealsproject.data.repository.Repository.getAllCategories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeContainerView = supportFragmentManager
            .findFragmentById(R.id.homeContainerView) as NavHostFragment
        navController = homeContainerView.navController
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}