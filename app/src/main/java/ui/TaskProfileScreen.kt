
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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

private val ProfileTeal = Color(0xFF168C83)
private val ProfileDark = Color(0xFF086B65)
private val ProfileMint = Color(0xFFDDF7F0)
private val ProfileInk = Color(0xFF24413D)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskProfileScreen(
    totalTasks: Int,
    completedTasks: Int
) {
    Scaffold(
        containerColor = Color(0xFFF3FBF8),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profil Mahasiswa",
                        color = ProfileInk,
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .size(112.dp)
                    .background(
                        Brush.linearGradient(
                            listOf(ProfileTeal, ProfileDark)
                        ),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Foto profil",
                    tint = Color.White,
                    modifier = Modifier.size(68.dp)
                )
            }

            Text(
                "Mahasiswa Informatika",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = ProfileInk
            )

            Text(
                "Tetap produktif, satu tugas setiap langkah!",
                fontSize = 13.sp,
                color = ProfileDark
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(3.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    Text(
                        "Biodata Akademik",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = ProfileInk
                    )

                    ProfileInfo("Nama", "Salsabila Adnina Hadi")
                    ProfileInfo("NIM", "2411022")
                    ProfileInfo("Program Studi", "Informatika")
                    ProfileInfo("Kelas", "IFB5A")
                    ProfileInfo("Semester 5", "Semester Mahasiswa")
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = ProfileMint
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Assignment,
                            contentDescription = null,
                            tint = ProfileDark
                        )
                        Text(
                            "  Ringkasan Tugas",
                            color = ProfileInk,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ProfileStat("Total Tugas", totalTasks)
                        ProfileStat("Selesai", completedTasks)
                        ProfileStat(
                            "Belum Selesai",
                            (totalTasks - completedTasks).coerceAtLeast(0)
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.School,
                    contentDescription = null,
                    tint = ProfileTeal
                )
                Text(
                    "  Manajemen Tugas Mahasiswa",
                    color = ProfileDark,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun ProfileInfo(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            label,
            color = Color(0xFF71827D),
            fontSize = 12.sp
        )
        Text(
            value,
            color = ProfileInk,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun ProfileStat(label: String, value: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            value.toString(),
            color = ProfileDark,
            fontSize = 23.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            label,
            color = ProfileInk,
            fontSize = 11.sp
        )
    }
}
