package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.core.view.WindowCompat
import com.example.todoapp.ui.theme.TodoAppTheme
import androidx.activity.compose.setContent        // Enables setContent {}
import androidx.compose.foundation.layout.padding  // Enables modifier.padding()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            TodoAppTheme {
                val todoViewModel: TodoViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TodoListPage(todoViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
