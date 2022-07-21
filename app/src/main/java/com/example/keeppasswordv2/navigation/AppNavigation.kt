package com.example.keeppasswordv2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.keeppasswordv2.screens.FirstScreen
import com.example.keeppasswordv2.screens.FourthScreen
import com.example.keeppasswordv2.screens.SecondScreen
import com.example.keeppasswordv2.screens.ThirdScreen
import com.example.keeppasswordv2.views.MainViewModel


@Composable
fun AppNavigation(viewModel: MainViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route){
        // First Screen
        composable(route = AppScreens.FirstScreen.route){
            FirstScreen(navController, viewModel)
        }

        // SecondScreen
        composable(route = AppScreens.SecondScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text"){
                type = NavType.StringType
            }
            )
        ){
            SecondScreen(navController, it.arguments?.getString("text"), viewModel)
        }

        // ThirdScreen
        composable(route = AppScreens.ThirdScreen.route + "/{text}",
            arguments = listOf(navArgument(name = "text"){
                type = NavType.StringType
            }
            )
        ){
            ThirdScreen(navController, it.arguments?.getString("text"), viewModel)
        }

        composable(route = AppScreens.FourthScreen.route){
            FourthScreen(navController = navController,
                viewModel = viewModel)
        }

    }

}