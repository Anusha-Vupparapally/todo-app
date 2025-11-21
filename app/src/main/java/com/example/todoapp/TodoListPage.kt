package com.example.todoapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R.drawable.delete
import com.example.todoapp.data.Todo
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage(
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier
) {
    val todoList by viewModel.todoList.observeAsState(listOf())
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            }, modifier = Modifier.padding(start = 8.dp)) {
                Text("Add")
            }
        }

        if (todoList.isEmpty()) {
            Text(
                text = "No Tasks Yet",
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(todoList) { index, item ->
                    TodoItem(item) { viewModel.deleteTodo(item.id) }
                }
            }
        }
    }
}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .background(MaterialTheme.colorScheme.inversePrimary, RoundedCornerShape(16.dp))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, dd/MM/yyyy", Locale.ENGLISH).format(item.createdAt),
                fontSize = 16.sp,
                color = Color.Blue
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = androidx.compose.ui.res.painterResource(delete),
                contentDescription = "Delete",
                tint = Color.Red
            )
        }
    }
}
