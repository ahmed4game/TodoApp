package com.ahmed.fms.todo.dao

import androidx.room.*
import com.ahmed.fms.todo.data.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * from todo_table WHERE categoryId = :categoryId ORDER BY id DESC")
    fun getTodosByCategory(categoryId: Int): Flow<List<Todo>>
}