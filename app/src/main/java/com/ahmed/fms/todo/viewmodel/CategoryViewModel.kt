package com.ahmed.fms.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.fms.todo.data.Category
import com.ahmed.fms.todo.repos.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository):
    ViewModel() {

    val allCategories = categoryRepository.allCategories

    fun addCategory(name: String) {
        viewModelScope.launch {
            categoryRepository.insert(Category(name = name))
        }
    }

    fun remove(category: Category) {
        viewModelScope.launch {
            categoryRepository.delete(category)
        }
    }
}