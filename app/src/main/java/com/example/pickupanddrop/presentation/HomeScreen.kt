package com.example.pickupanddrop.presentation


import android.graphics.drawable.Icon
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pickupanddrop.DeliveryDetails
import com.example.pickupanddrop.R
import com.example.pickupanddrop.common.ButtonwithIcon
import com.example.pickupanddrop.common.ClickableCard

@Composable
fun HomeScreen(
    navigateToUpcoming: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .statusBarsPadding()
    ) {
        ClickableCard(
            image = painterResource(id = R.drawable.gift),
            heading = "Welcome Offer! Get 30% off on your first 5 deliveries",
            subHeading = "Use coupon : ",
            coupon = "WELCOME30"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(color = Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.empty),
                    contentDescription = "No order",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(360.dp)
                        .height(350.dp)
                )

                Text(
                    text = "There are no active orders",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(24.dp))

                val context = LocalContext.current
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val context = LocalContext.current
        ExtendedFloatingActionButton(
            text = { Text(text = "Create Order") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = null
                )
            },
            onClick = {
                context.startActivity(Intent(context, DeliveryDetails::class.java))
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToUpcoming = {}
    )
}