package com.example.tugasfront

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.tugasfront.ui.theme.TugasFRONTTheme
import androidx.compose.ui.platform.LocalContext


class MethodDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasFRONTTheme {
                MethodDetailScreen()
            }
        }
    }
}

@Composable
fun MethodDetailScreen() {
    Scaffold(
        topBar = { TopAppBarWithBackButton() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                HeaderSection()
            }
            item {
                DescriptionSection()
            }
            item {
                StepsSection()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton() {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = "Metode",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5A7247)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Back",
                    tint = Color(0xFF5A7247)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color(0xFF5A7247),
            navigationIconContentColor = Color(0xFF5A7247)
        ),
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.hidroponik),
            contentDescription = "Header Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = "Hidroponik",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Composable
fun DescriptionSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Pengertian",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A7247),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Hidroponik adalah metode bercocok tanam tanpa menggunakan tanah, melainkan menggunakan larutan air yang mengandung nutrisi dan mineral. Tanaman tumbuh dengan menyerap nutrisi langsung melalui akarnya.",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun StepsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Cara Membuat",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5A7247),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        val steps = listOf(
            "siapkan bahan ray, benih, sayuran, arang dan polybag",
            "masukan arang ke dalam polybag",
            "masukan benih satu persatu ke dalam lubang tanaman",
            "taburkan kembali arang secukupnya untuk menutupi benih",
            "sirami benih yang sudah di tanam"
        )

        steps.forEachIndexed { index, step ->
            StepCard(stepNumber = index + 1, stepText = step)
        }
    }
}

@Composable
fun StepCard(stepNumber: Int, stepText: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFC8D6B9)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "$stepNumber.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5A7247),
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stepText,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MethodDetailScreenPreview() {
    TugasFRONTTheme {
        MethodDetailScreen()
    }
}
