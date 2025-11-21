package com.example.todoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.Todo
import com.example.todoapp.db.TodoDatabase
import com.example.todoapp.data.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TodoRepository
    val todoList: LiveData<List<Todo>>

    init {
        val dao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(dao)
        todoList = repository.allTodos
    }

    fun addTodo(title: String) {
        val todo = Todo(title = title)
        viewModelScope.launch {
            repository.insert(todo)
        }
    }

    fun deleteTodo(id: Int) {
        val todoToDelete = todoList.value?.find { it.id == id } ?: return
        viewModelScope.launch {
            repository.delete(todoToDelete)
        }
    }
}
