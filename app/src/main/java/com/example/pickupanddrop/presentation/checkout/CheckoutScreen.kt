package com.example.pickupanddrop.presentation.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun CheckoutUI(modifier: Modifier = Modifier.padding(16.dp)) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp, end = 16.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "Checkout",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = " 1 DROP ",
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
                text = " 14.1 KM ",
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

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "Choose Delivery Option",
                style = MaterialTheme.typography.bodyLarge,
//                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            DeliveryBanner(
                text = "Deliver now",
                desc = "We will assign a delivery partner immediately",
                price = "₹200",
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.height(4.dp))

            DeliveryBanner(
                text = "Schedule pickup",
                desc = "We will We will start looking for " +
                        "delivery partner 15 mins before the scheduled time",
                price = "₹200",
                modifier = Modifier.weight(1f)
            )
        }

        Text(
            text = "Offers & Coupons",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Coupon(text = "D4BWELCOME", discount = "Save 100 on this order")
            Button(onClick = { /* Handle button click */ }) {
                Text(text = "APPLY")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "₹200",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { /* Handle button click */ }) {
                Text(text = "Pay Now")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(onClick = { /* Handle click on View Invoice */ })
        ) {
            Text(text = "View Invoice", style = MaterialTheme.typography.bodySmall)
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "View Invoice")
        }
    }
}

@Composable
fun DeliveryOption(text: String, price: String, modifier: Modifier = Modifier) {
    OutlinedCard(
        modifier = modifier.padding(vertical = 8.dp),
//        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = text, style = MaterialTheme.typography.bodyMedium)
            Text(text = price, style = MaterialTheme.typography.bodySmall)
            Text(
                text = "We will assign a delivery portitive immediately",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun DeliveryBanner(
    text: String,
    desc: String,
    price: String,
//    selected: Boolean,
//    onSelectedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedOption = remember { mutableStateOf("Option1") }

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
    ) {
//        Box(
//
//        ) {
        Row(
//                modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption.value == "Option1",
                onClick = { selectedOption.value = "Option1" },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.secondary,
                    unselectedColor = Color.White
                ),
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.weight(0.2f))

            Column {
                Text(text = text, style = MaterialTheme.typography.bodyMedium)
                Text(text = price, style = MaterialTheme.typography.bodySmall)
            }

//                Text(
//                    text = "We will assign a delivery portitive immediately",
//                    style = MaterialTheme.typography.bodySmall
//                )
//
//                Text(
//                    text = "Deliver Now",
////                style = MaterialTheme.typography.h5,
//                    color = Color.White,
//                    modifier = Modifier.weight(0.6f)
//                )
            Spacer(modifier = Modifier.weight(0.2f))
            Text(
                text = "₹180",
//                style = MaterialTheme.typography.h5,
                color = Color.White,
                modifier = Modifier.weight(0.2f)
            )
        }
//            Text(
//                text = "We will assign a delivery partner immediately",
//            style = MaterialTheme.typography.body2,
//                color = Color.White,
//                modifier = Modifier
//                    .padding(bottom = 16.dp)
//            )
    }

}
//}


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
    CheckoutUI()
}
