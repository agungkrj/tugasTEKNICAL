package com.example.tugasfront

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tugasfront.ui.theme.TugasFRONTTheme
import androidx.compose.ui.platform.LocalContext


class CategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasFRONTTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomNavigationBarCategory(navController) }
                ) { innerPadding ->
                    CategoryScreenContent(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CategoryScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tanaman",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A7247),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(10) { index ->
                PlantCardItem(name = "Bayam")
            }
        }
    }
}


@Composable
fun PlantCardItem(name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(0xFFC8D6B9), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(0.8f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.up),
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
        )
        Text(name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun BottomNavigationBarCategory(navController: NavController) {
    val context = LocalContext.current // Dapatkan context di sini

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
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.LightGray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.jenis), contentDescription = "Category") },
            label = { Text("Category") },
            selected = true,
            onClick = { /* Handle Category Navigation */ },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Black,
                unselectedIconColor = Color.LightGray,
                indicatorColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(painter = painterResource(id = R.drawable.about), contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = {
                val intent = Intent(context, ProfileActivity::class.java)
                context.startActivity(intent)
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.White,
                unselectedIconColor = Color.LightGray,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryScreenContent() {
    TugasFRONTTheme {
        Scaffold(
            bottomBar = { BottomNavigationBarCategory(navController = rememberNavController()) }
        ) {
            CategoryScreenContent(modifier = Modifier.padding(it))
        }
    }
}
