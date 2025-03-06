package com.ahmed.fms.todo.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ahmed.fms.todo.data.Todo
import com.ahmed.fms.todo.ui.theme.TODOTheme
import com.ahmed.fms.todo.viewmodel.TodoViewModel


@Composable
fun TodoListScreen(viewModel: TodoViewModel, navController: NavHostController, categoryId: Int) {
    val todos by viewModel.todos.collectAsState()

    LaunchedEffect(categoryId) {
        viewModel.setSelectedCategoryId(categoryId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var newTask by remember { mutableStateOf("") }

        OutlinedTextField(
            value = newTask,
            onValueChange = { newTask = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            label = { Text(text = "New Task") }
        )

        Button(
            onClick = {
                if (newTask.isNotBlank()) {
                    viewModel.addTodo(newTask)
                    newTask = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text(text = "Add Task")
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(todos) { todo ->
                todoItem(todo = todo, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun todoItem(todo: Todo, viewModel: TodoViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { viewModel.toggleComplete(todo) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = todo.title, modifier = Modifier.weight(1f))
        Checkbox(checked = todo.isCompleted, onCheckedChange = { viewModel.toggleComplete(todo) })
        IconButton(onClick = { viewModel.deleteTodo(todo) }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    TODOTheme {
        Box(modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, shape = RoundedCornerShape(8.dp)) // Shadow with rounded corners
            .border(
                1.dp,
                Color.DarkGray,
                shape = RoundedCornerShape(8.dp)
            ) // Border with rounded corners
            .clip(RoundedCornerShape(8.dp)) // Clip content to match shape
//            .border(border = BorderStroke(2.dp, Color.Black))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Ahmed", modifier = Modifier.weight(1f))
                Checkbox(checked = true, onCheckedChange = {  })
                IconButton(onClick = {  }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}
