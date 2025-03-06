package com.ahmed.fms.todo.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ahmed.fms.todo.data.Category
import com.ahmed.fms.todo.viewmodel.CategoryViewModel

@Composable
fun DashboardScreen(viewModel: CategoryViewModel, navController: NavHostController) {

    val categories by viewModel.allCategories.collectAsState(initial = emptyList())
            
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        CategoryField(navController, viewModel)
        Text(text = "Categories")
        LazyColumn (modifier = Modifier.fillMaxSize()) {
            items(categories) { category -> 
                categoryItem(category = category, viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun CategoryField(
    navController: NavHostController,
    viewModel: CategoryViewModel
) {
    var newCategory by remember {
        mutableStateOf("")
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = newCategory,
            onValueChange = { newCategory = it },
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier.weight(1f),
            trailingIcon = {
                IconButton(onClick = {
                    if (newCategory.isNotBlank()) {
                        viewModel.addCategory(newCategory)
                    } else {

                    }
//                    navController.navigate("todo_list")
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            label = {
                Text("Add Category")
            }
        )
    }
}

@Composable
private fun Header() {
    Text(
        text = "Dashboard",
        fontSize = 39.sp,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.Cursive,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier.padding(top = 8.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
                .height(2.dp)
        )
        Text(
            text = "X",
            modifier = Modifier.weight(.5f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Gray
        )
        Spacer(
            modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
                .height(2.dp)
        )
    }
}

@Composable
fun categoryItem(category: Category, viewModel: CategoryViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /*viewModel.toggleComplete(todo)*/ },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = category.name, modifier = Modifier.weight(1f))
        IconButton(onClick = { /*viewModel.deleteTodo(todo)*/ }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}