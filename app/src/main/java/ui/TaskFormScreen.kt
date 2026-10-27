
package com.example.manajementugasmahasiswa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.School
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

private val FormTeal = Color(0xFF168C83)
private val FormDeepTeal = Color(0xFF086B65)
private val FormMint = Color(0xFFDDF7F0)
private val FormBackground = Color(0xFFF3FBF8)

@Composable
fun TaskFormScreen(
    task: Task? = null,
    onBack: () -> Unit,
    onSave: (
        title: String,
        description: String,
        dueDate: String,
        priority: String,
        category: String
    ) -> Unit
) {
    var title by remember(task?.id) {
        mutableStateOf(task?.title.orEmpty())
    }
    var description by remember(task?.id) {
        mutableStateOf(task?.description.orEmpty())
    }
    var dueDate by remember(task?.id) {
        mutableStateOf(task?.dueDate.orEmpty())
    }
    var priority by remember(task?.id) {
        mutableStateOf(task?.priority ?: "Sedang")
    }
    var category by remember(task?.id) {
        mutableStateOf(task?.category ?: "Kuliah")
    }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FormBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF42CBB4), FormDeepTeal)
                    )
                )
                .padding(horizontal = 20.dp, vertical = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = Color.White
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    if (task == null) "Tugas Baru ✨" else "Edit Tugas ✨",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    "Rencanakan langkah suksesmu",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
            Text("✦", color = Color.White, fontSize = 30.sp)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Card(
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(FormMint),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.School,
                                contentDescription = null,
                                tint = FormTeal
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                "Detail tugas",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                color = FormDeepTeal
                            )
                            Text(
                                "Isi informasi tugasmu di sini",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    FormLabel("Judul tugas *")
                    OutlinedTextField(
                        value = title,
                        onValueChange = {
                            title = it
                            error = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Contoh: Laporan praktikum") },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp)
                    )

                    FormLabel("Deskripsi")
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Catat detail tugas di sini...")
                        },
                        minLines = 3,
                        shape = RoundedCornerShape(16.dp)
                    )

                    FormLabel("Tanggal tenggat")
                    OutlinedTextField(
                        value = dueDate,
                        onValueChange = { dueDate = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Contoh: 30-10-2026") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.CalendarMonth,
                                contentDescription = null,
                                tint = FormTeal
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp)
                    )

                    FormLabel("Prioritas")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Rendah", "Sedang", "Tinggi").forEach { option ->
                            FilterChip(
                                selected = priority == option,
                                onClick = { priority = option },
                                label = { Text(option) },
                                modifier = Modifier.weight(1f),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = FormMint,
                                    selectedLabelColor = FormDeepTeal
                                )
                            )
                        }
                    }

                    FormLabel("Kategori")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Kuliah", "Proyek", "Lainnya").forEach { option ->
                            FilterChip(
                                selected = category == option,
                                onClick = { category = option },
                                label = { Text(option) },
                                modifier = Modifier.weight(1f),
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = FormMint,
                                    selectedLabelColor = FormDeepTeal
                                )
                            )
                        }
                    }

                    if (error) {
                        Text(
                            "Judul tugas wajib diisi.",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 13.sp
                        )
                    }

                    Button(
                        onClick = {
                            if (title.isBlank()) {
                                error = true
                            } else {
                                onSave(
                                    title.trim(),
                                    description.trim(),
                                    dueDate.trim(),
                                    priority,
                                    category
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(17.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = FormTeal
                        )
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null)
                        Spacer(Modifier.width(9.dp))
                        Text(
                            if (task == null) "Simpan Tugas" else "Simpan Perubahan",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(FormMint)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("🌱", fontSize = 28.sp)
                Spacer(Modifier.width(12.dp))
                Text(
                    "Tips: pecah tugas besar menjadi langkah kecil agar lebih mudah diselesaikan.",
                    color = FormDeepTeal,
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )
            }
        }
    }
}

@Composable
private fun FormLabel(text: String) {
    Text(
        text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        color = FormDeepTeal
    )
}
