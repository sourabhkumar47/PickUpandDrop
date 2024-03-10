package com.example.pickupanddrop.presentation.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pickupanddrop.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun DeliveryOption(
    text: String,
    desc: String,
    totalPrice: Float,
    selectedPrice: MutableState<Float>,
    selectedOption: MutableState<String>,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp))
            .clickable {
                if (selectedOption.value != text) {
                    selectedOption.value = text
                    selectedPrice.value = totalPrice
                    onSelected()
                }
            }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            RadioButton(
                selected = selectedOption.value == text,
                onClick = {
                    if (selectedOption.value != text) {
                        selectedOption.value = text
                        selectedPrice.value = totalPrice
                        onSelected()
                    }
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Blue,
                    unselectedColor = Color.Gray
                ),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        onSelected()
                    }

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
                    modifier = Modifier.width(200.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = totalPrice.toString(),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black
            )
        }
    }
}

@Composable
fun OfferCard(
    discount: Discount,
    appliedDiscount: MutableState<Boolean>,
    onApplyClicked: () -> Unit,
    onShowAllOffersClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val applied = remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = discount.discountCode,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        onApplyClicked()
//                        appliedDiscount.value = !appliedDiscount.value
                    },
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(
                        text = if (appliedDiscount.value) "APPLIED" else "APPLY",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color =
                        if (appliedDiscount.value) colorResource(id = R.color.grey)
                        else colorResource(id = R.color.lightBlue)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = R.drawable.discount),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Get ₹${discount.discountAmount} off on this order",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                thickness = 2.dp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View all coupons ",
                    style = MaterialTheme.typography.titleSmall,
                    color = colorResource(id = R.color.black),
                    modifier = Modifier.clickable { onShowAllOffersClicked() }
                )

                Spacer(modifier = Modifier.width(0.2.dp))

                Image(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun NewButton(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp))
            .background(colorResource(id = R.color.orange))
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .width(40.dp)
            .height(18.dp)
    ) {
        Text(
            text = "NEW",
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.headlineMedium.copy(fontSize = 16.sp).fontSize,
            fontStyle = MaterialTheme.typography.headlineMedium.fontStyle,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun NewButtonPreview() {
    NewButton()
}

@Preview(showBackground = true)
@Composable
fun OfferCardPreview() {
    OfferCard(
        discount = Discount(
            discountCode = "FLAT50",
            discountAmount = 50
        ),
        onApplyClicked = {},
        appliedDiscount = remember { mutableStateOf(false) },
        onShowAllOffersClicked = {}
    )
}