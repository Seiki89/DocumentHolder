package com.seiki.android.docholder.screens.work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.seiki.android.docholder.APP
import com.seiki.android.docholder.R
import com.seiki.android.docholder.databinding.ActivityWorkBinding

class WorkActivity : AppCompatActivity() {
    private lateinit var bind: ActivityWorkBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityWorkBinding.inflate(layoutInflater)
        setContentView(bind.root)
        APP = this
        navController = Navigation.findNavController(this, R.id.docFragmentHolder)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.docFragmentHolder) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)



    }
}