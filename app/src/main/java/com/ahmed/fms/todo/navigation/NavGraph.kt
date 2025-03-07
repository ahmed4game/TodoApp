package com.ahmed.fms.todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmed.fms.todo.compose.DashboardScreen
import com.ahmed.fms.todo.compose.TodoListScreen
import com.ahmed.fms.todo.viewmodel.CategoryViewModel
import com.ahmed.fms.todo.viewmodel.TodoViewModel

@Composable
fun NavGraph(todoViewModel: TodoViewModel, categoryViewModel: CategoryViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dashboard") {
        composable(
            "todo_list/{categoryId}",
            arguments = listOf(navArgument("categoryId") {type = NavType.IntType})
            ) {backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            TodoListScreen(todoViewModel, categoryId)
        }
        composable("dashboard") {
            DashboardScreen(categoryViewModel, navController)
        }
    }
}