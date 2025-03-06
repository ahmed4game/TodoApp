package com.ahmed.fms.todo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("category_table")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
