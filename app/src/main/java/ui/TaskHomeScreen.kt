
package com.example.manajementugasmahasiswa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manajementugasmahasiswa.data.Task
import com.example.manajementugasmahasiswa.viewmodel.TaskViewModel

private val Teal = Color(0xFF168C83)
private val DeepTeal = Color(0xFF086B65)
private val Mint = Color(0xFFDDF7F0)
private val PaleMint = Color(0xFFF3FBF8)
private val Ink = Color(0xFF24413D)

@Composable
fun TaskHomeScreen(
    viewModel: TaskViewModel,
    onAddTask: () -> Unit,
    onEditTask: (Task) -> Unit,
    onOpenTask: (Task) -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()

    val completed = tasks.count { it.isCompleted }
    val pending = tasks.size - completed

    Scaffold(
        containerColor = PaleMint,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTask,
                containerColor = Teal,
                contentColor = Color.White,
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah tugas")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 16.dp,
                bottom = 100.dp
            ),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                HeaderDecoration(total = tasks.size)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    StatisticCard(
                        title = "Semua Tugas",
                        value = tasks.size.toString(),
                        icon = { Icon(Icons.Default.School, null) },
                        modifier = Modifier.weight(1f)
                    )
                    StatisticCard(
                        title = "Berjalan",
                        value = pending.toString(),
                        icon = { Icon(Icons.Default.DateRange, null) },
                        modifier = Modifier.weight(1f)
                    )
                    StatisticCard(
                        title = "Selesai",
                        value = completed.toString(),
                        icon = { Icon(Icons.Default.CheckCircle, null) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        "Daftar Tugas ✨",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Ink
                    )
                    Text(
                        "Satu langkah kecil, satu tugas selesai.",
                        fontSize = 13.sp,
                        color = Teal
                    )
                }
            }

            if (tasks.isEmpty()) {
                item {
                    EmptyTaskCard(onAddTask)
                }
            } else {
                items(tasks, key = { it.id }) { task ->
                    TaskCard(
                        task = task,
                        onClick = { onOpenTask(task) },
                        onEdit = { onEditTask(task) },
                        onDelete = { viewModel.removeTask(task) },
                        onToggle = { viewModel.toggleTaskStatus(task) }
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderDecoration(total: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(0xFF42CBB4),
                        Teal,
                        DeepTeal
                    )
                )
            )
            .padding(22.dp)
    ) {
        Box(
            modifier = Modifier
                .size(115.dp)
                .offset(x = 195.dp, y = (-45).dp)
                .background(Color.White.copy(alpha = 0.12f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(65.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 12.dp, y = 14.dp)
                .background(Color.White.copy(alpha = 0.12f), CircleShape)
        )

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.TaskAlt,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "TASKLY",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 3.sp
                )
                Spacer(Modifier.weight(1f))
                Text("✦  ✧  ✦", color = Color.White, fontSize = 17.sp)
            }

            Text(
                "Halo, Mahasiswa! 👋",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Atur tugasmu, raih tujuanmu.",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp
            )

            Spacer(Modifier.height(6.dp))

            Surface(
                color = Color.White.copy(alpha = 0.18f),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    " ✨  Kamu memiliki $total tugas ",
                    color = Color.White,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 9.dp
                    ),
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun StatisticCard(
    title: String,
    value: String,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 15.dp
            ),
            verticalArrangement = Arrangement.spacedBy(9.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Mint),
                contentAlignment = Alignment.Center
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides Teal
                ) {
                    icon()
                }
            }

            Text(
                value,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DeepTeal
            )
            Text(
                title,
                fontSize = 11.sp,
                color = Ink,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun TaskCard(
    task: Task,
    onClick: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onToggle: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Hapus tugas?") },
            text = { Text("Tugas \"${task.title}\" akan dihapus permanen.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDelete()
                    }
                ) {
                    Text("Hapus", color = Color(0xFFD14343))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Batal")
                }
            }
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Teal
                    )
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 7.dp)
                ) {
                    Text(
                        task.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Ink
                    )
                    if (task.description.isNotBlank()) {
                        Spacer(Modifier.height(5.dp))
                        Text(
                            task.description,
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                }

                Text(
                    if (task.isCompleted) "✓" else "✦",
                    color = Teal,
                    fontSize = 20.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LabelChip("📚 ${task.category}")
                LabelChip("⚑ ${task.priority}")
            }

            if (task.dueDate.isNotBlank()) {
                Text(
                    "📅 Tenggat: ${task.dueDate}",
                    color = Teal,
                    fontSize = 12.sp
                )
            }

            HorizontalDivider(color = Mint)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onClick) {
                    Text("Detail", color = Teal)
                }
                Spacer(Modifier.weight(1f))
                IconButton(onClick = onEdit) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit tugas",
                        tint = Teal
                    )
                }
                IconButton(onClick = { showDeleteDialog = true }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Hapus tugas",
                        tint = Color(0xFFD76C6C)
                    )
                }
            }
        }
    }
}

@Composable
private fun LabelChip(text: String) {
    Surface(
        color = Mint,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text,
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 6.dp
            ),
            fontSize = 11.sp,
            color = DeepTeal
        )
    }
}

@Composable
private fun EmptyTaskCard(onAddTask: () -> Unit) {
    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("🌱  ✨  📚", fontSize = 34.sp)
            Text(
                "Belum ada tugas!",
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp,
                color = Ink
            )
            Text(
                "Yuk, tambahkan tugas pertamamu\nagar semua kegiatan lebih teratur.",
                color = Color.Gray,
                fontSize = 13.sp,
                lineHeight = 20.sp
            )
            Button(
                onClick = onAddTask,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Teal
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(6.dp))
                Text("Tambah Tugas")
            }
        }
    }
}
