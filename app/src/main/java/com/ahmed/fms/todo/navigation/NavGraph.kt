package com.ahmed.fms.todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmed.fms.todo.compose.TodoListScreen
import com.ahmed.fms.todo.viewmodel.TodoViewModel

@Composable
fun NavGraph(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "todo_list") {
        composable("todo_list") {
            TodoListScreen(viewModel)
        }
    }
}