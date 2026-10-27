
package com.example.manajementugasmahasiswa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manajementugasmahasiswa.data.Task
import com.example.manajementugasmahasiswa.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks = repository.allTasks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addTask(
        title: String,
        description: String,
        dueDate: String,
        priority: String,
        category: String
    ) {
        if (title.isBlank()) return

        viewModelScope.launch {
            repository.insertTask(
                Task(
                    title = title.trim(),
                    description = description.trim(),
                    dueDate = dueDate,
                    priority = priority,
                    category = category
                )
            )
        }
    }

    fun editTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun removeTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun toggleTaskStatus(task: Task) {
        viewModelScope.launch {
            repository.updateTaskStatus(
                taskId = task.id,
                completed = !task.isCompleted
            )
        }
    }
}
