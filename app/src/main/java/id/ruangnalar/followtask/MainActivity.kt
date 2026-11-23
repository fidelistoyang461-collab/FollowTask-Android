package id.ruangnalar.followtask

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val Background = Color(0xFF080B16)
private val NeonBlue = Color(0xFF00D9FF)
private val NeonPurple = Color(0xFF9B5CFF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FollowTaskApp()
        }
    }
}

@Composable
fun FollowTaskApp() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var page by remember { mutableStateOf("Beranda") }
    var status by remember {
        mutableStateOf("Belum ada konfirmasi tugas")
    }
    var loading by remember { mutableStateOf(false) }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    "FOLLOW TASK",
                    color = NeonBlue,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Black
                )

                Text(
                    "CYBERPUNK PROFESSIONAL",
                    color = NeonPurple,
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(28.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Beranda", "Instagram", "Telegram").forEach {
                        Button(
                            onClick = { page = it },
                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                    if (page == it) NeonPurple
                                    else Color(0xFF20283A)
                            )
                        ) {
                            Text(it)
                        }
                    }
                }

                Spacer(Modifier.height(25.dp))

                when (page) {
                    "Beranda" -> {
                        Text(
                            "DASHBOARD",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(15.dp))

                        InfoCard("STATUS AKTIVITAS", status)
                        Spacer(Modifier.height(12.dp))
                        InfoCard("KONEKSI PR.SOCIAL", "Belum tersinkronisasi")
                        Spacer(Modifier.height(12.dp))
                        InfoCard("SISTEM", "Follow manual")
                    }

                    "Instagram" -> {
                        Text(
                            "INSTAGRAM TASK",
                            color = NeonBlue,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(15.dp))

                        Text(
                            "Buka Instagram dan kerjakan tugas secara manual.",
                            color = Color.White
                        )

                        Spacer(Modifier.height(20.dp))

                        Button(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.instagram.com/")
                                )
                                context.startActivity(intent)
                            }
                        ) {
                            Text("BUKA INSTAGRAM")
                        }

                        Spacer(Modifier.height(15.dp))

                        Button(
                            enabled = !loading,
                            onClick = {
                                scope.launch {
                                    loading = true
                                    delay(5000)
                                    status = "Konfirmasi lokal tercatat"
                                    loading = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NeonPurple
                            )
                        ) {
                            Text(
                                if (loading) "MENUNGGU..."
                                else "KONFIRMASI TUGAS"
                            )
                        }

                        Spacer(Modifier.height(12.dp))

                        Text(
                            "Konfirmasi lokal tidak menjamin tugas " +
                                "telah diverifikasi Instagram atau PR.social.",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }

                    "Telegram" -> {
                        Text(
                            "TELEGRAM WORKER",
                            color = NeonBlue,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(15.dp))

                        Text(
                            "Gunakan alur resmi PR.social.",
                            color = Color.White
                        )

                        Spacer(Modifier.height(20.dp))

                        Button(
                            onClick = {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://pr.social/")
                                )
                                context.startActivity(intent)
                            }
                        ) {
                            Text("BUKA PR.SOCIAL")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFF151D30),
                RoundedCornerShape(14.dp)
            )
            .padding(18.dp)
    ) {
        Text(
            title,
            color = NeonBlue,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(7.dp))

        Text(
            value,
            color = Color.White,
            fontSize = 15.sp
        )
    }
}
