package com.example.tiketpemesanan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TicketOrderScreen()
        }
    }
}

@Composable
fun TicketOrderScreen() {

    val ticketPrice = 25000

    var ticketCount by remember {
        mutableStateOf(1)
    }

    val totalPrice = ticketPrice * ticketCount

    // GREEN COLOR PALETTE
    val green = Color(0xFF4CAF50)
    val lightGreen = Color(0xFFE8F5E9)
    val darkGreen = Color(0xFF1B5E20)
    val backgroundGreen = Color(0xFFF4FBF5)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundGreen
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {

            // ICON
            Text(
                text = "🎟️",
                fontSize = 46.sp
            )

            // TITLE
            Text(
                text = "Pemesanan Tiket",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = darkGreen
            )

            Text(
                text = "Pesan tiket dengan mudah!",
                fontSize = 15.sp,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // MAIN CARD
            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    // HARGA TIKET
                    Text(
                        text = "Harga Tiket",
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )

                    Text(
                        text = "Rp25.000",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = green
                    )

                    Text(
                        text = "per tiket",
                        color = Color.Gray,
                        fontSize = 13.sp
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    // JUMLAH TIKET
                    Text(
                        text = "Jumlah Tiket",
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween,

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        // BUTTON MINUS
                        Button(
                            onClick = {

                                if (ticketCount > 1) {
                                    ticketCount--
                                }

                            },

                            modifier = Modifier.size(52.dp),

                            shape = CircleShape,

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor = green
                                ),

                            contentPadding =
                                androidx.compose.foundation.layout.PaddingValues(
                                    0.dp
                                )
                        ) {

                            Text(
                                text = "−",
                                fontSize = 28.sp,
                                color = Color.White
                            )
                        }

                        // JUMLAH
                        Text(
                            text = "$ticketCount",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkGreen
                        )

                        // BUTTON PLUS
                        Button(
                            onClick = {
                                ticketCount++
                            },

                            modifier = Modifier.size(52.dp),

                            shape = CircleShape,

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor = green
                                ),

                            contentPadding =
                                androidx.compose.foundation.layout.PaddingValues(
                                    0.dp
                                )
                        ) {

                            Text(
                                text = "+",
                                fontSize = 28.sp,
                                color = Color.White
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    // TOTAL BAYAR CARD
                    Card(
                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(16.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = lightGreen
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Total Bayar",
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray
                            )

                            Text(
                                text = formatRupiah(totalPrice),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = darkGreen
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    // RESET BUTTON
                    Button(
                        onClick = {
                            ticketCount = 1
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(14.dp),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = green
                            )
                    ) {

                        Text(
                            text = "↻  RESET",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )
        }
    }
}


// FORMAT RUPIAH
fun formatRupiah(nominal: Int): String {

    val format =
        NumberFormat.getNumberInstance(
            Locale("id", "ID")
        )

    return "Rp${format.format(nominal)}"
}