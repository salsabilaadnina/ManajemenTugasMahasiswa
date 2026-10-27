
package com.example.manajementugasmahasiswa.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val description: String = "",
    val dueDate: String = "",
    val priority: String = "Sedang",
    val category: String = "Kuliah",
    val isCompleted: Boolean = false
)
