package com.ahmed.fms.todo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ahmed.fms.todo.dao.CategoryDao
import com.ahmed.fms.todo.dao.TodoDao
import com.ahmed.fms.todo.data.Category
import com.ahmed.fms.todo.data.Todo

@Database(entities = [Todo::class,Category::class], version = 2)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun categoryDao(): CategoryDao

    companion object {
//        @volatile
        private var INSTANCE: TodoDatabase? = null

        private val MIGRATION_1_2 = object: Migration(1,2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // create category table
                db.execSQL("CREATE TABLE IF NOT EXISTS 'category_table' ('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'name' TEXT NOT NULL)")
                // Insert a data
                db.execSQL("INSERT INTO 'category_table' ('id','name') VALUES (0, 'Uncategorized')")
                // Add category ID to todo table
                db.execSQL("ALTER TABLE 'todo_table' ADD COLUMN 'categoryId' INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build().also { INSTANCE = it }
            }
        }
    }
}