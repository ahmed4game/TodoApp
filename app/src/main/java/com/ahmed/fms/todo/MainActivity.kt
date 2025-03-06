package com.ahmed.fms.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmed.fms.todo.navigation.NavGraph
import com.ahmed.fms.todo.ui.theme.TODOTheme
import com.ahmed.fms.todo.viewmodel.CategoryViewModel
import com.ahmed.fms.todo.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val todoViewModel: TodoViewModel = hiltViewModel()
                    val categoryViewModel: CategoryViewModel = hiltViewModel()
                    NavGraph(todoViewModel, categoryViewModel)
                }
            }
        }
    }
}