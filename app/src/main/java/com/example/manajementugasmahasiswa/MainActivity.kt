
package com.example.manajementugasmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.manajementugasmahasiswa.data.Task
import com.example.manajementugasmahasiswa.data.TaskDatabase
import com.example.manajementugasmahasiswa.repository.TaskRepository
import com.example.manajementugasmahasiswa.ui.TaskDetailScreen
import com.example.manajementugasmahasiswa.ui.TaskFormScreen
import com.example.manajementugasmahasiswa.ui.TaskHomeScreen
import com.example.manajementugasmahasiswa.ui.TaskProfileScreen
import com.example.manajementugasmahasiswa.ui.TaskStatisticsScreen
import com.example.manajementugasmahasiswa.ui.theme.ManajemenTugasMahasiswaTheme
import com.example.manajementugasmahasiswa.viewmodel.TaskViewModel
import com.example.manajementugasmahasiswa.viewmodel.TaskViewModelFactory

private data class BottomMenu(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = TaskDatabase.getDatabase(applicationContext)
        val repository = TaskRepository(database.taskDao())
        val factory = TaskViewModelFactory(repository)

        setContent {
            ManajemenTugasMahasiswaTheme {
                val navController = rememberNavController()
                val taskViewModel: TaskViewModel = viewModel(factory = factory)
                val tasks by taskViewModel.tasks.collectAsState()

                val currentEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentEntry?.destination?.route

                val menus = listOf(
                    BottomMenu("home", "Beranda", Icons.Default.Home),
                    BottomMenu("tasks", "Tugas", Icons.Default.Assignment),
                    BottomMenu("statistics", "Statistik", Icons.Default.QueryStats),
                    BottomMenu("profile", "Profil", Icons.Default.Person)
                )

                val showBottomBar = currentRoute in
                        listOf("home", "tasks", "statistics", "profile")

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                menus.forEach { menu ->
                                    NavigationBarItem(
                                        selected = currentRoute == menu.route,
                                        onClick = {
                                            navController.navigate(menu.route) {
                                                popUpTo("home") {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                menu.icon,
                                                contentDescription = menu.label
                                            )
                                        },
                                        label = { Text(menu.label) }
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(
                            if (showBottomBar) innerPadding
                            else androidx.compose.foundation.layout.PaddingValues()
                        )
                    ) {
                        composable("home") {
                            TaskHomeScreen(
                                viewModel = taskViewModel,
                                onAddTask = {
                                    navController.navigate("add")
                                },
                                onEditTask = { task ->
                                    navController.navigate("edit/${task.id}")
                                },
                                onOpenTask = { task ->
                                    navController.navigate("detail/${task.id}")
                                }
                            )
                        }

                        composable("tasks") {
                            TaskHomeScreen(
                                viewModel = taskViewModel,
                                onAddTask = {
                                    navController.navigate("add")
                                },
                                onEditTask = { task ->
                                    navController.navigate("edit/${task.id}")
                                },
                                onOpenTask = { task ->
                                    navController.navigate("detail/${task.id}")
                                }
                            )
                        }

                        composable("statistics") {
                            TaskStatisticsScreen(
                                totalTasks = tasks.size,
                                completedTasks = tasks.count { it.isCompleted },
                                highPriorityTasks = tasks.count {
                                    it.priority.equals("Tinggi", ignoreCase = true) &&
                                            !it.isCompleted
                                }
                            )
                        }

                        composable("profile") {
                            TaskProfileScreen(
                                totalTasks = tasks.size,
                                completedTasks = tasks.count { it.isCompleted }
                            )
                        }

                        composable("add") {
                            TaskFormScreen(
                                onBack = { navController.popBackStack() },
                                onSave = { title, description, dueDate, priority, category ->
                                    taskViewModel.addTask(
                                        title = title,
                                        description = description,
                                        dueDate = dueDate,
                                        priority = priority,
                                        category = category
                                    )
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            route = "detail/{taskId}",
                            arguments = listOf(
                                navArgument("taskId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { entry ->
                            val taskId = entry.arguments?.getInt("taskId")
                            val task = tasks.find { it.id == taskId }

                            if (task != null) {
                                TaskDetailScreen(
                                    task = task,
                                    onBack = { navController.popBackStack() },
                                    onEdit = {
                                        navController.navigate("edit/${task.id}")
                                    }
                                )
                            }
                        }

                        composable(
                            route = "edit/{taskId}",
                            arguments = listOf(
                                navArgument("taskId") {
                                    type = NavType.IntType
                                }
                            )
                        ) { entry ->
                            val taskId = entry.arguments?.getInt("taskId")
                            val task = tasks.find { it.id == taskId }

                            if (task != null) {
                                TaskFormScreen(
                                    task = task,
                                    onBack = { navController.popBackStack() },
                                    onSave = { title, description, dueDate, priority, category ->
                                        taskViewModel.editTask(
                                            task.copy(
                                                title = title.trim(),
                                                description = description.trim(),
                                                dueDate = dueDate,
                                                priority = priority,
                                                category = category
                                            )
                                        )
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
