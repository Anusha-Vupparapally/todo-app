package com.example.todoapp.data

import androidx.lifecycle.LiveData
import com.example.todoapp.db.TodoDao

class TodoRepository(private val dao: TodoDao) {

    val allTodos: LiveData<List<Todo>> = dao.getAllTodos()

    suspend fun insert(todo: Todo) = dao.insertTodo(todo)
    suspend fun delete(todo: Todo) = dao.deleteTodo(todo)
}
