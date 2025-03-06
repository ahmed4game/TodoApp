package com.ahmed.fms.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.fms.todo.data.Todo
import com.ahmed.fms.todo.repos.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository): ViewModel() {

    private val _selectedCategoryId = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    val todos = _selectedCategoryId.flatMapLatest { categoryId ->
        todoRepository.getTodosByCategory(categoryId)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setSelectedCategoryId(categoryId: Int) {
        _selectedCategoryId.value = categoryId
    }

    fun addTodo(title: String) {
        viewModelScope.launch {
            todoRepository.insert(Todo(title = title))
        }
    }

    fun toggleComplete(todo: Todo) {
        viewModelScope.launch {
            todoRepository.update(todo.copy(isCompleted = !todo.isCompleted))
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.delete(todo)
        }
    }
}