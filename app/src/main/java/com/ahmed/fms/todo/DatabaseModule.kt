package com.ahmed.fms.todo

import android.content.Context
import com.ahmed.fms.todo.dao.CategoryDao
import com.ahmed.fms.todo.dao.TodoDao
import com.ahmed.fms.todo.db.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return TodoDatabase.getDatabase(context)
    }

    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }

    @Provides
    fun provideCategoryDao(database: TodoDatabase): CategoryDao {
        return database.categoryDao()
    }
}