package com.example.pickupanddrop.presentation.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pickupanddrop.R

@Composable
fun CheckoutUI(
    drops: Int,
    distance: Float,
    totalPrice: Float,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
    ) {

        val extra = 30
        val pricePerKm = 15

        val selectedDeliveryOption = remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .padding(bottom = 8.dp, end = 16.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "Checkout",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = colorResource(id = R.color.grey))
                    .padding(8.dp)
            ) {
                Text(
                    text = " $drops DROP ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.width(2.5.dp))

            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = colorResource(id = R.color.grey))
                    .padding(8.dp)
            ) {
                Text(
                    text = " $distance KM ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        ) {}

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Choose Delivery Option",
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        val selectedPrice = remember { mutableFloatStateOf(0f) }

        Column {

            DeliveryOption(
                text = "Deliver now",
                desc = "We will assign a delivery partner immediately",
                totalPrice = distance * pricePerKm + extra,
                selectedPrice = selectedPrice,
                selectedOption = selectedDeliveryOption,
                onSelected = {
                    selectedDeliveryOption.value = "Deliver now"
                    selectedPrice.floatValue = distance * pricePerKm + extra
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DeliveryOption(
                text = "Schedule pickup",
                desc = "We will We will start looking for " +
                        "delivery partner 15 mins before the scheduled time",
                totalPrice = distance * pricePerKm,
                selectedPrice = selectedPrice,
                selectedOption = selectedDeliveryOption,
                onSelected = {
                    selectedDeliveryOption.value = "Schedule pickup"
                    selectedPrice.floatValue = distance * pricePerKm
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.offer),
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "Offers & Coupons",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontStyle = MaterialTheme.typography.headlineMedium.fontStyle
            )

            Spacer(modifier = Modifier.width(4.dp))

            NewButton()

        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        ) {}

        Spacer(modifier = Modifier.height(8.dp))

        OfferCard(
            discount = Discount(
                discountCode = "FLAT50",
                discountAmount = 50,
            ),
            onApplyClicked = { },
            onShowAllOffersClicked = { })

        Column(
            modifier = Modifier
        ) {
            Spacer(modifier = Modifier.weight(1f))
            OutlinedCard(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                ) {
                    Text(
                        text = "${selectedPrice.floatValue}",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.weight(0.5f)
                    )
                    Button(
                        onClick = { }
                    ) {
                        Text(text = "Pay Now")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CheckoutUIPreview() {
    CheckoutUI(
        drops = 1,
        distance = 10.0f,
        totalPrice = 14.0f
    )
}
