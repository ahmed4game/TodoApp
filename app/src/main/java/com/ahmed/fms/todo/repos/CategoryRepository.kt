package com.ahmed.fms.todo.repos

import com.ahmed.fms.todo.dao.CategoryDao
import com.ahmed.fms.todo.data.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao){

    val allCategories = categoryDao.getAllCategories()

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun delete(category: Category) {
        categoryDao.delete(category)
    }
}