package com.vadesan.practica01dsm.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vadesan.practica01dsm.presentation.auth.LoginScreen
import com.vadesan.practica01dsm.presentation.auth.RegisterScreen
import com.vadesan.practica01dsm.presentation.home.HomeScreen

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login")
    {
        composable(route = "login") { LoginScreen(navController) }
        composable(route = "register") { RegisterScreen(navController) }
        composable(route = "home") {
            HomeScreen(navController)
        }
    }
}