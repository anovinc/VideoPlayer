package com.example.videoplayer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.videoplayer.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_VideoPlayer)
        setContentView(R.layout.activity_main)
        setNavigationGraph()
    }
    private fun setNavigationGraph() {
        val isUserLoggedIn = intent.getBooleanExtra("isUserLoggedIn", false)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.navigation)
        if(isUserLoggedIn) {
            navGraph.setStartDestination(R.id.videoHostFragment)
        }
        else {
            navGraph.setStartDestination(R.id.userLoginFragment)
        }
        navController.graph = navGraph
    }
}