package com.example.pickupanddrop.presentation.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun DeliveryOption(
    text: String,
    desc: String,
    totalPrice: Float,
    selectedPrice: MutableState<Float>,
    isSelected: Boolean = false,
    selectedOption: MutableState<Boolean>,
    onSelected: () -> Unit,
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
                    onSelected()
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
