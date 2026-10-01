package com.example.praktikum3


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ProfileCard()
                }
            }
        }
    }
}


/*
 * REUSABLE COMPOSABLE 1
 * Header foto profil dan nama
 */
@Composable
fun ProfileHeader(
    name: String,
    role: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Box digunakan sebagai container foto profil
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.primary
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto Profil",
                modifier = Modifier.size(55.dp),
                tint = Color.White
            )
        }

        Spacer(
            modifier = Modifier.width(16.dp)
        )

        Column {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.size(4.dp)
            )

            Text(
                text = role,
                fontSize = 15.sp,
                color = Color.Gray
            )
        }
    }
}


/*
 * REUSABLE COMPOSABLE 2
 * Item informasi profile
 */
@Composable
fun InfoItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(22.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column {
            Text(
                text = label,
                fontSize = 13.sp,
                color = Color.Gray
            )

            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


/*
 * REUSABLE COMPOSABLE 3
 * Card utama profile
 */
@Composable
fun ProfileCard() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                // Header
                ProfileHeader(
                    name = "Arbani Hafizh",
                    role = "Mahasiswa Teknik Informatika"
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                // Bio
                Text(
                    text = "Tentang Saya",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.size(6.dp)
                )

                Text(
                    text = "Saya adalah mahasiswa yang tertarik "
                            + "dengan pengembangan aplikasi mobile "
                            + "menggunakan Kotlin dan Jetpack Compose.",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.size(16.dp)
                )

                // Informasi
                Text(
                    text = "Informasi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                InfoItem(
                    icon = Icons.Default.Email,
                    label = "Email",
                    value = "arbani@example.com"
                )

                InfoItem(
                    icon = Icons.Default.Phone,
                    label = "Phone",
                    value = "0812-3456-7890"
                )

                InfoItem(
                    icon = Icons.Default.LocationOn,
                    label = "Location",
                    value = "Indonesia"
                )

                Spacer(
                    modifier = Modifier.size(16.dp)
                )

                // Button
                Button(
                    onClick = {
                        // Aksi tombol
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Hubungi Saya"
                    )
                }
            }
        }
    }
}