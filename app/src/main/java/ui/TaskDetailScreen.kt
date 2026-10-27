
package com.example.manajementugasmahasiswa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.manajementugasmahasiswa.data.Task

private val DetailTeal = Color(0xFF168C83)
private val DetailDarkTeal = Color(0xFF086B65)
private val DetailMint = Color(0xFFDDF7F0)
private val DetailInk = Color(0xFF24413D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    task: Task,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Detail Tugas",
                        fontWeight = FontWeight.Bold,
                        color = DetailInk
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Kembali",
                            tint = DetailDarkTeal
                        )
                    }
                }
            )
        },
        containerColor = Color(0xFFF3FBF8)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFF3FBF8), Color(0xFFE0F6EF))
                    )
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DetailTeal
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.School,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(
                            "  TUGAS MAHASISWA",
                            color = DetailMint,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(18.dp))

                    Text(
                        task.title,
                        color = Color.White,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(Modifier.height(12.dp))

                    Text(
                        if (task.isCompleted) "✓ Tugas selesai"
                        else "◷ Belum selesai",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    Text(
                        "Informasi Tugas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DetailInk
                    )

                    DetailRow(
                        icon = Icons.Default.CalendarMonth,
                        label = "Tenggat Waktu",
                        value = task.dueDate.ifBlank { "Belum ditentukan" }
                    )

                    DetailRow(
                        icon = Icons.Default.Flag,
                        label = "Prioritas",
                        value = task.priority
                    )

                    DetailRow(
                        icon = Icons.Default.Category,
                        label = "Kategori",
                        value = task.category
                    )

                    Text(
                        "Deskripsi",
                        fontWeight = FontWeight.Bold,
                        color = DetailInk
                    )

                    Text(
                        task.description.ifBlank {
                            "Belum ada deskripsi untuk tugas ini."
                        },
                        color = Color(0xFF617570),
                        lineHeight = 23.sp
                    )
                }
            }

            Spacer(Modifier.height(2.dp))

            Button(
                onClick = onEdit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DetailDarkTeal
                )
            ) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = null
                )
                Text(
                    "  Edit Tugas",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun DetailRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = null,
            tint = DetailTeal
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp)
        ) {
            Text(
                label,
                color = Color(0xFF71827D),
                fontSize = 12.sp
            )
            Text(
                value,
                color = DetailInk,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
