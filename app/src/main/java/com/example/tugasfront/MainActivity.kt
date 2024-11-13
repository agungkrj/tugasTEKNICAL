package com.example.tugasfront

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tugasfront.ui.theme.TugasFRONTTheme
import androidx.compose.ui.layout.ContentScale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasFRONTTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { GreetingSection() }
        item { PlantSection() }
        item { MethodSection() }
    }
}

@Composable
fun GreetingSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.border),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Selamat datang User!", fontSize = 16.sp, color = Color(0xFF5A7247))
            Text("Ayo lihat progres tanamanmu!", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}


@Composable
fun PlantSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Tanaman", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5A7247))
            Text("Lainnya", fontSize = 14.sp, color = Color.Gray)
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) { index ->
                PlantCard(name = "Tanaman $index")
            }
        }
    }
}

@Composable
fun PlantCard(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(120.dp)
            .background(Color(0xFFC8D6B9), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.up),
            contentDescription = name,
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
        )
        Text(
            text = name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A7247)
        )

    }
}

@Composable
fun MethodSection() {
    val context = LocalContext.current
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Metode", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5A7247))
            Text("Lainnya", fontSize = 14.sp, color = Color.Gray)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) { index ->
                MethodCard(name = "hidroponik $index") {
                    val intent = Intent(context, MethodDetailActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }
}

@Composable
fun MethodCard(name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color(0xFFC8D6B9), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.hidroponik),
            contentDescription = name,
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        )
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A7247),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.panah),
            contentDescription = "Arrow Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val context = LocalContext.current
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        containerColor = Color(0xFF5A7247)
    ) {
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.home), contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { }
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
            selected = false,
            onClick = {
                val intent = Intent(context, ProfileActivity::class.java)
                context.startActivity(intent)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TugasFRONTTheme {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController = rememberNavController()) }
        ) {
            HomeScreen(modifier = Modifier.padding(it))
        }
    }
}
