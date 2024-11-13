package com.example.tugasfront

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tugasfront.ui.theme.TugasFRONTTheme
import androidx.compose.ui.platform.LocalContext


class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasFRONTTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBarProfile(navController) }
                ) { innerPadding ->
                    ProfileScreenContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ProfileScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A7247),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .background(Color(0xFFE6E6E6), shape = CircleShape)
                .padding(4.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        ProfileItem(label = "Nama Lengkap", value = "said agung")
        ProfileItem(label = "Email", value = "username@gmail.com")
        ProfileItem(label = "Asal Perguruan", value = "Politeknik Negeri Batam")
        ProfileItem(label = "Jurusan", value = "Teknik Informatika")
        ProfileItem(label = "Tanggal Lahir", value = "27/10/2024")
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = label, fontSize = 14.sp, color = Color.Gray)
                Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Icon(
                painter = painterResource(id = R.drawable.pensil),
                contentDescription = "Edit",
                modifier = Modifier.size(20.dp),
                tint = Color.Gray
            )
        }
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun BottomNavigationBarProfile(navController: NavController) {
    val context = LocalContext.current // Mendapatkan konteks saat ini

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        containerColor = Color(0xFF5A7247)
    ) {
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.home), contentDescription = "Home") },
            label = { Text("Home") },
            selected = false,
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.jenis), contentDescription = "Category") },
            label = { Text("Category") },
            selected = false,
            onClick = {
                val intent = Intent(context, CategoryActivity::class.java)
                context.startActivity(intent)
            }
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.about), contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = true,
            onClick = { /* Navigate to Profile */ },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreenContent() {
    TugasFRONTTheme {
        Scaffold(
            bottomBar = { BottomNavigationBarProfile(navController = rememberNavController()) }
        ) {
            ProfileScreenContent(modifier = Modifier.padding(it))
        }
    }
}
