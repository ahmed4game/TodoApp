package com.ahmed.fms.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.fms.todo.data.Todo
import com.ahmed.fms.todo.repos.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository): ViewModel() {

    val allTodos = repository.allTodos

    fun addTodo(title: String) {
        viewModelScope.launch {
            repository.insert(Todo(title = title))
        }
    }

    fun toggleComplete(todo: Todo) {
        viewModelScope.launch {
            repository.update(todo.copy(isCompleted = !todo.isCompleted))
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }
}