package com.example.pickupanddrop.presentation.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pickupanddrop.R

@Composable
fun CheckoutUI(
    drops: Int,
    distance: Float,
    totalPrice: Float,
    modifier: Modifier = Modifier.padding(16.dp)
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
//        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val extra = 30
        val pricePerKm = 15

        Row(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp, end = 16.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "Checkout",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = " $drops DROP ",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = colorResource(id = R.color.grey))
                    .clip(
                        RoundedCornerShape(160.dp)
                    )
            )
            Spacer(modifier = Modifier.width(1.dp))
            Text(
                text = " $distance KM ",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = colorResource(id = R.color.grey))
                    .clip(shape = RoundedCornerShape(160.dp))
            )
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
                isSelected = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            DeliveryOption(
                text = "Schedule pickup",
                desc = "We will We will start looking for " +
                        "delivery partner 15 mins before the scheduled time",
                totalPrice = distance * pricePerKm,
                selectedPrice = selectedPrice
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Offers & Coupons",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        ) {}


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
//                    .background(color = MaterialTheme.colorScheme.onPrimaryContainer)
                    .padding(all = 16.dp)
            ) {
                Coupon(text = "Coupon Applied", discount = "10% off")
                Coupon(text = "Offers", discount = "5% off")
            }
        }

        Box(
            modifier = modifier
                .fillMaxSize()) {
            OutlinedCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                    .background(color = MaterialTheme.colorScheme.background)
                        .padding(all = 16.dp)
                ) {
                    Text(
                        text = "${selectedPrice.floatValue}",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.weight(0.5f)
                    )
                    Button(onClick = { /* Handle button click */ }) {
                        Text(text = "Pay Now")
                    }
                }
            }
        }

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//                .clickable(onClick = { /* Handle click on View Invoice */ })
//        ) {
//            Text(text = "View Invoice", style = MaterialTheme.typography.bodySmall)
//            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "View Invoice")
//        }
    }
}


@Composable
fun DeliveryOption(
    text: String,
    desc: String,
    totalPrice: Float,
    selectedPrice: MutableState<Float>,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier
) {
    val selectedOption = remember { mutableStateOf(isSelected) }

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .clickable {
                selectedOption.value = !selectedOption.value
                if (selectedOption.value) {
                    selectedPrice.value = totalPrice
                }
            }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            RadioButton(
                selected = selectedOption.value,
                onClick = {
                    selectedOption.value = !selectedOption.value
                    if (selectedOption.value) {
                        selectedPrice.value = totalPrice
                    }
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Blue,
                    unselectedColor = Color.Gray
                ),
                modifier = Modifier.padding(start = 8.dp)
            )

            Column {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.width(200.dp)

                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.width(250.dp)
                )
            }

            Text(
                text = totalPrice.toString(),
                style = MaterialTheme.typography.labelLarge,
                color = Color.Black
            )
        }
    }
}

@Composable
fun Coupon(text: String, discount: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(vertical = 8.dp)) {
        Text(text = text, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = discount, style = MaterialTheme.typography.bodySmall)
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
