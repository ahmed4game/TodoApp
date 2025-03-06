package com.ahmed.fms.todo.repos

import com.ahmed.fms.todo.dao.TodoDao
import com.ahmed.fms.todo.data.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    val allTodos: Flow<List<Todo>> = todoDao.getTodoList()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun update(todo: Todo) {
        todoDao.update(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}