package com.example.pickupanddrop.delivery.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pickupanddrop.delivery.Data.LocationData


@Composable
fun LocationDetailsCard(
    modifier: Modifier = Modifier,
    locationData: LocationData,
    onEditDetails: () -> Unit
) {

    val labelFontWeight = MaterialTheme.typography.labelMedium.fontWeight

    ElevatedCard(
        modifier = modifier.border(1.dp, Color.Gray.copy(0.5f), RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),

        ) {
        Column() {
            // pick up location and owner details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray.copy(0.1f))
                    .padding(8.dp)
            ) {
                Row {
                    Text(
                        text = locationData.locationType,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                        fontWeight = labelFontWeight,
                        color = Color.Gray
                    )
                    Text(text = " *", color = Color.Red.copy(0.8f))
                }

                Spacer(modifier = Modifier.height(12.dp))

                // franchise name and its location
                Row {
                    Text(
                        text = "${locationData.franchiseName} ",
                        fontWeight = MaterialTheme.typography.labelMedium.fontWeight
                    )
                    Text(
                        text = locationData.franchiseLocation,
                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                        fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // owner name, phone no, and edit details option
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        imageVector = Icons.Filled.Call,
                        contentDescription = "phone no of owner",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    // owner name and phone no
                    Text(
                        text = "${locationData.ownerName} (${locationData.ownerPhoneNo})",
                        color = Color.Gray,
                        fontWeight = labelFontWeight,
                        fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                        fontSize = MaterialTheme.typography.labelMedium.fontSize
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    // edit details clickable text
                    Text(
                        modifier = Modifier.clickable {
                            onEditDetails()
                        },
                        text = "Edit Details",
                        fontWeight = FontWeight(800),
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        color = Color.Blue.copy(0.6f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Gray.copy(0.5f))
            ) {}

            // OTP
            val checkedStateForOTP = rememberSaveable {
                mutableStateOf(false)
            }
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.size(16.dp),
                    checked = checkedStateForOTP.value, onCheckedChange = {
                        checkedStateForOTP.value = it
                    })
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "OTP for ${locationData.otpFor}",
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = labelFontWeight,
                    fontStyle = MaterialTheme.typography.labelMedium.fontStyle
                )
            }
        }

    }
}

@Composable
fun DropLocationCard(modifier: Modifier = Modifier) {
    val blueBorderColor = Color.Blue.copy(0.7f)
    ElevatedCard(
        modifier = modifier.border(1.dp, blueBorderColor, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier.border(1.dp, blueBorderColor, CircleShape)) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "add drop location",
                    tint = blueBorderColor
                )
            }
            Text(text = "Add drop Location")
        }
    }
}

@Preview
@Composable
fun DropLocationCardPreview() {
    DropLocationCard()
}

@Preview
@Composable
fun PickUpLocationCardPreview() {
    LocationDetailsCard(
        locationData = LocationData(
            locationType = "PICKUP LOCATION",
            franchiseName = "Petuk Ji G.Noida Franchise",
            franchiseLocation = "IT waala digital success D, 296 Greater Noida Wandra",
            ownerName = "Vinay",
            ownerPhoneNo = "7970783256",
            otpFor = "pickup"
        ),
        onEditDetails = {}
    )
}
