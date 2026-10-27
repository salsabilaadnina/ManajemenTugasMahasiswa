
package com.example.manajementugasmahasiswa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PendingActions
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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

private val StatsTeal = Color(0xFF168C83)
private val StatsDark = Color(0xFF086B65)
private val StatsMint = Color(0xFFDDF7F0)
private val StatsInk = Color(0xFF24413D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskStatisticsScreen(
    totalTasks: Int,
    completedTasks: Int,
    highPriorityTasks: Int
) {
    val pendingTasks = (totalTasks - completedTasks).coerceAtLeast(0)
    val progress = if (totalTasks > 0) {
        completedTasks.toFloat() / totalTasks.toFloat()
    } else {
        0f
    }

    Scaffold(
        containerColor = Color(0xFFF3FBF8),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Statistik Produktivitas",
                        color = StatsInk,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFF3FBF8), Color(0xFFDDF7F0))
                    )
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = StatsTeal
                ),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Icon(
                        Icons.Default.TrendingUp,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Text(
                        "Produktivitas Kamu",
                        color = StatsMint,
                        fontSize = 14.sp
                    )

                    Text(
                        "${(progress * 100).toInt()}%",
                        color = Color.White,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(9.dp),
                        color = Color.White,
                        trackColor = Color(0xFF66BDB0)
                    )

                    Text(
                        "$completedTasks dari $totalTasks tugas sudah selesai",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }
            }

            Text(
                "Ringkasan Tugas",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = StatsInk
            )

            StatsCard(
                title = "Total Tugas",
                value = totalTasks,
                description = "Semua tugas yang tercatat",
                icon = {
                    Icon(
                        Icons.Default.Assignment,
                        contentDescription = null,
                        tint = StatsTeal
                    )
                }
            )

            StatsCard(
                title = "Tugas Selesai",
                value = completedTasks,
                description = "Tugas yang sudah dituntaskan",
                icon = {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = StatsDark
                    )
                }
            )

            StatsCard(
                title = "Belum Selesai",
                value = pendingTasks,
                description = "Tugas yang masih perlu dikerjakan",
                icon = {
                    Icon(
                        Icons.Default.PendingActions,
                        contentDescription = null,
                        tint = Color(0xFFCC8A27)
                    )
                }
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = StatsMint
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Prioritas Tinggi",
                            fontWeight = FontWeight.Bold,
                            color = StatsInk
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            "Tugas yang perlu perhatian lebih",
                            fontSize = 12.sp,
                            color = StatsDark
                        )
                    }

                    Text(
                        highPriorityTasks.toString(),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = StatsDark
                    )
                }
            }
        }
    }
}

@Composable
private fun StatsCard(
    title: String,
    value: Int,
    description: String,
    icon: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(StatsMint, RoundedCornerShape(16.dp))
                    .padding(12.dp)
            ) {
                icon()
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp)
            ) {
                Text(
                    title,
                    color = StatsInk,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    description,
                    color = Color(0xFF71827D),
                    fontSize = 11.sp
                )
            }

            Text(
                value.toString(),
                color = StatsDark,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
